package com.brewing;

import java.awt.Color;
import java.awt.image.BufferedImage;
import net.runelite.client.ui.overlay.infobox.InfoBox;
import net.runelite.client.ui.overlay.infobox.InfoBoxPriority;

public class BrewingBarrel extends InfoBox
{
	private final String location;
	private final int barrel;
	private final BrewingPlugin plugin;
	private final BrewingConfig config;

	BrewingBarrel(String location, int barrel_varbit, BufferedImage image, BrewingPlugin plugin, BrewingConfig config)
	{
		super(image, plugin);
		this.barrel = barrel_varbit;
		this.location = location;
		this.plugin = plugin;
		this.config = config;
		setPriority(InfoBoxPriority.LOW);
	}

	@Override
	public String getText()
	{
		return this.location.substring(0, 1);
	}

	@Override
	public String getTooltip()
	{
		return this.location + " - " + BrewingBarrelState.toString(barrel);
	}

	@Override
	public Color getTextColor()
	{
		if(BrewingBarrelState.isDrainable(barrel))
		{
			return Color.RED;
		}
		else if(BrewingBarrelState.hasMatureContents(barrel))
		{
			return Color.BLUE;
		}
		else if(BrewingBarrelState.hasNormalContents(barrel))
		{
			return Color.GREEN;
		}
		return Color.WHITE;
	}

	@Override
	public boolean render()
	{
		return canDisplayBarrel() && canDisplayBarrelCond();
	}

	private boolean canDisplayBarrel() {
		if (config.displayBarrels() == BrewingConfig.DisplayMode.ALL)
		{
			return true;
		}
		else if (location == plugin.KELDAGRIM_NAME && config.displayBarrels() == BrewingConfig.DisplayMode.KELDAGRIM)
		{
			return true;
		}
		else if (location == plugin.PORT_PHASMATYS_NAME && config.displayBarrels() == BrewingConfig.DisplayMode.PORT_PHASMATYS)
		{
			return true;
		}
		else if (location == plugin.ALDARIN_NAME && config.displayBarrels() == BrewingConfig.DisplayMode.ALDARIN)
		{
			return true;
		}
		return false;
	}

	private boolean canDisplayBarrelCond()
	{
		if(config.barrelDisplayCond() == BrewingConfig.BarrelState.ALWAYS)
		{
			return true;
		}
		else if(barrel != BrewingBarrelState.EMPTY.getValue() && config.barrelDisplayCond() == BrewingConfig.BarrelState.NOT_EMPTY)
		{
			return true;
		}
		else if(BrewingBarrelState.isFull(barrel) && config.barrelDisplayCond() == BrewingConfig.BarrelState.FULL)
		{
			return true;
		}
		return false;
	}
}
