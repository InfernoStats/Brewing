package com.infernostats;

import lombok.Getter;
import lombok.Setter;
import net.runelite.client.ui.overlay.infobox.InfoBox;

import java.awt.Color;
import java.awt.image.BufferedImage;

@Getter
@Setter
public class BrewingInfoBox extends InfoBox {
    private String location;
    private BrewingState brewState;
    private boolean visible;

    public BrewingInfoBox(BufferedImage image, BrewingPlugin plugin, String location, BrewingState state)
    {
        super(image, plugin);
        this.location = location;
        this.brewState = state;
        this.visible = false;
    }

    @Override
    public String getText()
    {
        return this.location.substring(0, 1);
    }

    @Override
    public String getTooltip()
    {
        return this.location + " - " + brewState.toString();
    }

    @Override
    public Color getTextColor()
    {
        if (this.brewState.isFinished())
        {
            if (this.brewState == BrewingState.BAD_ALE)
                return Color.RED;
            else
                return Color.GREEN;
        }
        else if (this.brewState.isPartial())
		{
			return Color.YELLOW;
		}
        return Color.WHITE;
    }
}

