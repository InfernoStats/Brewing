package com.infernostats;

import net.runelite.client.ui.overlay.infobox.InfoBox;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BrewingInfoBox extends InfoBox {
    private BrewingPlugin plugin;
    private BrewingConfig config;
    private BrewingLocation location;
    private BrewingState brewState;
    private boolean visible;

    public BrewingInfoBox(BrewingPlugin plugin, BrewingConfig config,
                          BufferedImage image, BrewingLocation location, BrewingState state)
    {
        super(image, plugin);
        this.plugin = plugin;
        this.config = config;
        this.location = location;
        this.brewState = state;
        this.visible = false;
    }

    @Override
    public String getText()
    {
        return this.location.toString().substring(0, 1);
    }

    @Override
    public String getTooltip()
    {
        return this.location.toString() + " - " + brewState.toString();
    }

    @Override
    public Color getTextColor()
    {
        if (this.brewState.finished())
        {
            if (this.brewState == BrewingState.BAD_ALE)
                return Color.RED;
            else
                return Color.GREEN;
        }
        return Color.WHITE;
    }

    public void setBrewState(BrewingState state)
    {
        this.brewState = state;
    }

    public void setVisible(boolean isVisible)
    {
        this.visible = isVisible;
    }

    public boolean isVisible()
    {
        return this.visible;
    }
}
