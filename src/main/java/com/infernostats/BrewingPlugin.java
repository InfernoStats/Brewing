package com.infernostats;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.GameTick;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;

import javax.inject.Inject;
import java.awt.image.BufferedImage;

@Slf4j
@PluginDescriptor(
        name = "Brewing",
        description = "Brewing Overlay"
)
public class BrewingPlugin extends Plugin {
	BrewingLocation keldagrim = null;
	BrewingLocation phasmatys = null;

    private static final String CONFIG_GROUP = "BrewingConfig";

    @Inject
    private Client client;

    @Inject
    private BrewingConfig config;

    @Inject
    private ItemManager itemManager;

    @Inject
    private InfoBoxManager infoBoxManager;

    @Inject
    private ConfigManager configManager;

    @Override
    protected void startUp() {
        BufferedImage calquatKeg = itemManager.getImage(ItemID.CALQUAT_KEG);

		keldagrim = new KeldagrimBrewing(this, config, calquatKeg);
		phasmatys = new PhasmatysBrewing(this, config, calquatKeg);
    }

    @Override
    protected void shutDown() {
		infoBoxManager.removeIf(BrewingInfoBox.class::isInstance);

        keldagrim = null;
        phasmatys = null;
    }

    @Provides
    BrewingConfig getConfig(ConfigManager configManager) {
        return configManager.getConfig(BrewingConfig.class);
    }

    @Subscribe
    public void onConfigChanged(ConfigChanged e)
    {
        if (!e.getGroup().equals(CONFIG_GROUP))
        {
            return;
        }

        keldagrim.RevalidateInfoBox();
        phasmatys.RevalidateInfoBox();
    }

    @Subscribe
    public void onGameTick(GameTick e)
    {
    	keldagrim.UpdateBrewState();
    	phasmatys.UpdateBrewState();

		keldagrim.RevalidateInfoBox();
		phasmatys.RevalidateInfoBox();
    }

	protected void addInfoBox(BrewingInfoBox infoBox)
	{
		infoBoxManager.addInfoBox(infoBox);
	}

	protected void removeInfoBox(BrewingInfoBox infoBox)
	{
		infoBoxManager.removeInfoBox(infoBox);
	}

    protected int getVarbitValue(final int varbit)
	{
		return client.getVarbitValue(varbit);
	}
}