package com.brewing;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.stream.Stream;

import net.runelite.client.ui.overlay.infobox.InfoBox;
import net.runelite.client.ui.overlay.infobox.InfoBoxPriority;

public class BrewingVat extends InfoBox
{
	private final String location;
	private final int the_stuff;
	private final int vat;
	private final BrewingPlugin plugin;
	private final BrewingConfig config;

	BrewingVat(String location, int vat_val, int stuff_val, BufferedImage image, BrewingPlugin plugin, BrewingConfig config)
	{
		super(image, plugin);
		this.the_stuff = stuff_val;
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
		return plugin.getVatStateColor(vat);
	}

	@Override
	public boolean render()
	{
		// This checks user settings mode and current vat conditions
		return (config.displayVats() == BrewingConfig.DisplayMode.BOTH ||
				(location == plugin.KELDAGRIM_NAME && config.displayVats() == BrewingConfig.DisplayMode.KELDAGRIM) ||
				(location == plugin.PORT_PHASMATYS_NAME && config.displayVats() == BrewingConfig.DisplayMode.PORT_PHASMATYS)) &&
				 (config.vatDisplayCond() == BrewingConfig.VatState.ALWAYS ||
						 (vat != BrewingVatState.EMPTY.getValue() && config.vatDisplayCond() == BrewingConfig.VatState.PARTIAL_AND_COMPLETION) ||
						 (Stream.of(BrewingVatState.fromInt(vat)).anyMatch(BrewingVatState.COMPLETE_NORMAL_STATES::contains) || Stream.of(BrewingVatState.fromInt(vat)).anyMatch(BrewingVatState.COMPLETE_MATURE_STATES::contains)) && config.vatDisplayCond() == BrewingConfig.VatState.COMPLETION);
	}
}
