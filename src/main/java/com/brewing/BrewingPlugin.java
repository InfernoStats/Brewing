package com.brewing;

import com.google.inject.Provides;
import java.awt.image.BufferedImage;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.ItemID;
import net.runelite.api.events.VarbitChanged;
import static net.runelite.api.MenuAction.RUNELITE_OVERLAY_CONFIG;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;
import net.runelite.client.ui.overlay.OverlayManager;
import static net.runelite.client.ui.overlay.OverlayManager.OPTION_CONFIGURE;
import net.runelite.client.ui.overlay.OverlayMenuEntry;
import net.runelite.client.util.ImageUtil;

@Slf4j
@PluginDescriptor(
		name = "Brewing",
		description = "Brewing Overlay",
		tags = {"cooking", "skilling", "overlay"}
)
public class BrewingPlugin extends Plugin {
	private static final String CONFIG_GROUP = "BrewingConfig";

	@Inject
	private Client client;

	@Inject
	private ItemManager itemManager;

	@Inject
	private InfoBoxManager infoBoxManager;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private BrewingOverlay brewingOverlay;

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

	private final BufferedImage VAT_IMAGE = ImageUtil.loadImageResource(getClass(), "/vat.png");
	private final BufferedImage BARREL_IMAGE = ImageUtil.loadImageResource(getClass(), "/barrel.png");
	private final BufferedImage THE_STUFF_IMAGE = itemManager.getImage(ItemID.THE_STUFF);

	@Provides
	BrewingConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(BrewingConfig.class);
	}

	@Override
	protected void startUp() throws Exception
	{
		OverlayMenuEntry menuEntry = new OverlayMenuEntry(RUNELITE_OVERLAY_CONFIG, OPTION_CONFIGURE, "Brewing overlay");

		brewingOverlay.getMenuEntries().add(menuEntry);
		overlayManager.add(brewingOverlay);

		addInfoBoxes();
	}

	@Override
	protected void shutDown() throws Exception
    {
        brewingOverlay.getMenuEntries().clear();
        overlayManager.remove(brewingOverlay);
		removeInfoBoxes();
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged e)
	{
		if (!e.getGroup().equals(CONFIG_GROUP))
		{
			return;
		}

		removeInfoBoxes();
	}

	public void onVarbitChanged(VarbitChanged varbitChanged)
	{
		int var = varbitChanged.getVarbitId();

		if(var == KELDAGRIM_VAT_VARBIT || var == PORT_PHASMATYS_VAT_VARBIT ||
			var == KELDAGRIM_STUFF_VARBIT || var == PORT_PHASMATYS_STUFF_VARBIT ||
			var == KELDAGRIM_BARREL_VARBIT || var == PORT_PHASMATYS_BARREL_VARBIT)
		{
			removeInfoBoxes();
			addInfoBoxes();
		}

	}

	private void addInfoBoxes()
	{
		infoBoxManager.addInfoBox(new BrewingVat(KELDAGRIM_NAME, KELDAGRIM_VAT_VARBIT, KELDAGRIM_STUFF_VARBIT, VAT_IMAGE, this, config));
		infoBoxManager.addInfoBox(new BrewingVat(PORT_PHASMATYS_NAME, PORT_PHASMATYS_VAT_VARBIT, PORT_PHASMATYS_STUFF_VARBIT, VAT_IMAGE, this, config));
		infoBoxManager.addInfoBox(new BrewingBarrel(PORT_PHASMATYS_NAME, PORT_PHASMATYS_BARREL_VARBIT, BARREL_IMAGE, this, config));
		infoBoxManager.addInfoBox(new BrewingBarrel(PORT_PHASMATYS_NAME, PORT_PHASMATYS_BARREL_VARBIT, BARREL_IMAGE, this, config));
	}

	private void removeInfoBoxes()
	{
		infoBoxManager.removeIf(t -> t instanceof BrewingVat || t instanceof BrewingBarrel);
	}
}