package com.brewing;

import com.google.inject.Provides;
import java.awt.image.BufferedImage;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.VarbitChanged;
import net.runelite.client.Notifier;
import net.runelite.client.chat.ChatColorType;
import net.runelite.client.chat.ChatMessageBuilder;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.client.chat.QueuedMessage;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;
import net.runelite.client.util.ImageUtil;

@Slf4j
@PluginDescriptor(
		name = "Brewing",
		description = "Brewing Overlay",
		tags = {"cooking", "skilling", "overlay"}
)
public class BrewingPlugin extends Plugin {
	@Inject
	private Client client;

	@Inject
	private InfoBoxManager infoBoxManager;

	@Inject
	private Notifier notifier;

	@Inject
	private ChatMessageManager chatMessageManager;

	@Inject
	private BrewingConfig config;

	public static final String KELDAGRIM_NAME = "Keldagrim";
	private static final int KELDAGRIM_VAT_VARBIT = 736;
	private static final int KELDAGRIM_STUFF_VARBIT = 2294;
	private static final int KELDAGRIM_BARREL_VARBIT = 738;

	public static final String PORT_PHASMATYS_NAME = "Port Phasmatys";
	private static final int PORT_PHASMATYS_VAT_VARBIT = 737;
	private static final int PORT_PHASMATYS_STUFF_VARBIT = 2295;
	private static final int PORT_PHASMATYS_BARREL_VARBIT = 739;

	private boolean infoboxInit;

	private static final BufferedImage VAT_IMAGE = ImageUtil.loadImageResource(BrewingPlugin.class, "/com/brewing/vat.png");
	private static final BufferedImage BARREL_IMAGE = ImageUtil.loadImageResource(BrewingPlugin.class, "/com/brewing/barrel.png");
	private static final BufferedImage THE_STUFF_VAT_IMAGE = ImageUtil.loadImageResource(BrewingPlugin.class, "/com/brewing/vat_stuff.png");

	@Provides
	BrewingConfig provideConfig(ConfigManager configManager) {
		return configManager.getConfig(BrewingConfig.class);
	}

	@Override
	protected void startUp()
	{
		infoboxInit = false;
	}

	@Override
	protected void shutDown()
	{
		removeInfoBoxes();
		infoboxInit = false;
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged e) {
		if (!e.getGroup().equals(BrewingConfig.GROUP)) {
			return;
		}
		infoboxInit = false;
	}

	@Subscribe
	public void onVarbitChanged(VarbitChanged varbitChanged) {
		int var = varbitChanged.getVarbitId();

		if ((var == KELDAGRIM_VAT_VARBIT || var == PORT_PHASMATYS_VAT_VARBIT) &&
				(BrewingVatState.isCompletedNormal(varbitChanged.getValue()) ||
				 BrewingVatState.isCompletedMature(varbitChanged.getValue())))
		{
			if (config.notifyOnCompletion())
			{
				notifier.notify(
					"You have "
						+ BrewingVatState.toString(varbitChanged.getValue())
						+ " waiting to be collected in the "
						+ (var == KELDAGRIM_VAT_VARBIT ? "Keldagrim" : "Port Phasmatys")
						+ " vat."
				);
			}

			if (config.chatMessageOnCompletion())
			{
				sendChatMessage(
					"You have "
						+ BrewingVatState.toString(varbitChanged.getValue())
						+ " waiting to be collected in the "
						+ (var == KELDAGRIM_VAT_VARBIT ? "Keldagrim" : "Port Phasmatys")
						+ " vat."
				);
			}
		}

		if (var == KELDAGRIM_VAT_VARBIT || var == PORT_PHASMATYS_VAT_VARBIT ||
				var == KELDAGRIM_STUFF_VARBIT || var == PORT_PHASMATYS_STUFF_VARBIT ||
				var == KELDAGRIM_BARREL_VARBIT || var == PORT_PHASMATYS_BARREL_VARBIT) {
			removeInfoBoxes();
			addInfoBoxes();
		}
	}

	private void addInfoBoxes()
	{
		BufferedImage KELDAGRIM_VAT_IMAGE = client.getVarbitValue(KELDAGRIM_STUFF_VARBIT) == 1 ? THE_STUFF_VAT_IMAGE : VAT_IMAGE;
		BufferedImage PORT_PHASMATYS_VAT_IMAGE = client.getVarbitValue(PORT_PHASMATYS_STUFF_VARBIT) == 1 ? THE_STUFF_VAT_IMAGE : VAT_IMAGE;

		infoBoxManager.addInfoBox(new BrewingVat(KELDAGRIM_NAME, client.getVarbitValue(KELDAGRIM_VAT_VARBIT), KELDAGRIM_VAT_IMAGE, this, config));
		infoBoxManager.addInfoBox(new BrewingVat(PORT_PHASMATYS_NAME, client.getVarbitValue(PORT_PHASMATYS_VAT_VARBIT), PORT_PHASMATYS_VAT_IMAGE, this, config));
		infoBoxManager.addInfoBox(new BrewingBarrel(KELDAGRIM_NAME, client.getVarbitValue(KELDAGRIM_BARREL_VARBIT), BARREL_IMAGE, this, config));
		infoBoxManager.addInfoBox(new BrewingBarrel(PORT_PHASMATYS_NAME, client.getVarbitValue(PORT_PHASMATYS_BARREL_VARBIT), BARREL_IMAGE, this, config));
	}

	private void removeInfoBoxes()
	{
		infoBoxManager.removeIf(t -> t instanceof BrewingVat || t instanceof BrewingBarrel);
	}

	private void sendChatMessage(String chatMessage)
	{
		final String message = new ChatMessageBuilder()
			.append(ChatColorType.HIGHLIGHT)
			.append(chatMessage)
			.build();

		chatMessageManager.queue(
			QueuedMessage.builder()
				.type(ChatMessageType.CONSOLE)
				.runeLiteFormattedMessage(message)
				.build());
	}
}