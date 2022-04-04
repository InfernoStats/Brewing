package com.infernostats;

import java.awt.image.BufferedImage;

public class KeldagrimBrewing implements BrewingLocation {
	private final BrewingPlugin plugin;
	private final BrewingConfig config;
	private final BrewingInfoBox infoBox;

	private static final int KELDAGRIM_VAT_VARBIT = 736;

	KeldagrimBrewing(BrewingPlugin plugin, BrewingConfig config, BufferedImage infoBoxImage)
	{
		this.plugin = plugin;
		this.config = config;

		infoBox = new BrewingInfoBox(
				infoBoxImage,
				plugin,
				"Keldagrim",
				BrewingState.UNINITIALIZED
		);
	}

	public void UpdateBrewState()
	{
		final int varbitValue = plugin.getVarbitValue(KELDAGRIM_VAT_VARBIT);
		final BrewingState state = BrewingState.fromInt(varbitValue);

		infoBox.setBrewState(state);
	}

	private void AddInfoBox()
	{
		if (!infoBox.isVisible())
		{
			infoBox.setVisible(true);
			plugin.addInfoBox(infoBox);
		}
	}

	private void RemoveInfoBox()
	{
		if (infoBox.isVisible())
		{
			infoBox.setVisible(false);
			plugin.removeInfoBox(infoBox);
		}
	}

	public void RevalidateInfoBox()
	{
		if (!config.keldagrimInfobox())
		{
			RemoveInfoBox();
			return;
		}

		BrewingState state = infoBox.getBrewState();
		switch (config.infoboxState())
		{
			case ALWAYS:
				AddInfoBox();
				return;
			case PARTIAL_AND_COMPLETION:
				if (state.isPartial())
				{
					AddInfoBox();
					return;
				}
			case COMPLETION:
				if (state.isFinished())
				{
					AddInfoBox();
					return;
				}
			default:
				RemoveInfoBox();
		}
	}
}
