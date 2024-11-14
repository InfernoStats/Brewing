package com.brewing;

import java.awt.Color;
import java.awt.image.BufferedImage;
import net.runelite.client.ui.overlay.infobox.InfoBox;
import net.runelite.client.ui.overlay.infobox.InfoBoxPriority;

public class BrewingVat extends InfoBox
{
	private final String location;
	private final int vat;
	private final BrewingPlugin plugin;
	private final BrewingConfig config;

	BrewingVat(String location, int vat_val, BufferedImage image, BrewingPlugin plugin, BrewingConfig config)
	{
		super(image, plugin);
		this.vat = vat_val;
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
		return this.location + " - " + BrewingVatState.toString(vat);
	}

	@Override
	public Color getTextColor()
	{
		if(BrewingVatState.isBad(vat))
		{
			return Color.RED;
		}
		else if(BrewingVatState.isCompletedMature(vat))
		{
			return Color.BLUE;
		}
		else if(BrewingVatState.isCompletedNormal(vat))
		{
			return Color.GREEN;
		}
		else if(BrewingVatState.isPartial(vat))
		{
			return Color.YELLOW;
		}
		return Color.WHITE;
	}

	@Override
	public boolean render()
	{
		return canDisplayVat() && canDisplayVatCond();
	}

	private boolean canDisplayVat()
	{
		if(config.displayVats() == BrewingConfig.DisplayMode.NONE)
		{
			return false;
		}
		else if(config.displayVats() == BrewingConfig.DisplayMode.BOTH)
		{
			return true;
		}
		else if(location.equals(plugin.KELDAGRIM_NAME) && config.displayVats() == BrewingConfig.DisplayMode.KELDAGRIM)
		{
			return true;
		}
		else if(location.equals(plugin.PORT_PHASMATYS_NAME) && config.displayVats() == BrewingConfig.DisplayMode.PORT_PHASMATYS)
		{
			return true;
		}
		return false;
	}

	private boolean canDisplayVatCond()
	{
		if(config.vatDisplayCond() == BrewingConfig.VatState.ALWAYS)
		{
			return true;
		}
		else if(vat != BrewingVatState.EMPTY.getValue() && config.vatDisplayCond() == BrewingConfig.VatState.ANY_CONTENTS)
		{
			return true;
		}
		else if((BrewingVatState.isBad(vat) || BrewingVatState.isCompletedNormal(vat) || BrewingVatState.isCompletedMature(vat)) && config.vatDisplayCond() == BrewingConfig.VatState.COMPLETION)
		{
			return true;
		}
		return false;
	}
}
