package com.brewing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.Set;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.Skill;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.util.ColorUtil;

public class BrewingOverlay extends OverlayPanel
{/*
	private final Client client;
	private final BrewingConfig config;
	private final BrewingPlugin plugin;

	@Inject
	private BrewingOverlay(Client client, BrewingConfig config, BrewingPlugin plugin)
	{
		super(plugin);
		this.plugin = plugin;
		this.client = client;
		this.config = config;
		setPosition(OverlayPosition.TOP_LEFT);
		setPriority(OverlayPriority.LOW);
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{

		int nextChange = plugin.getChangeDownTicks();
		if (nextChange != -1)
		{
			panelComponent.getChildren().add(LineComponent.builder()
					.left("Next + restore in")
					.right(String.valueOf(plugin.getChangeTime(nextChange)))
					.build());
		}

		nextChange = plugin.getChangeUpTicks();
		if (nextChange != -1)
		{
			panelComponent.getChildren().add(LineComponent.builder()
					.left("Next - restore in")
					.right(String.valueOf(plugin.getChangeTime(nextChange)))
					.build());
		}

		if (plugin.canShowBoosts())
		{
			for (Skill skill : boostedSkills)
			{
				final int boosted = client.getBoostedSkillLevel(skill);
				final int base = client.getRealSkillLevel(skill);
				final int boost = boosted - base;
				final Color strColor = getTextColor(boost);
				String str;

				if (config.useRelativeBoost())
				{
					str = String.valueOf(boost);
					if (boost > 0)
					{
						str = "+" + str;
					}
				}
				else
				{
					str = ColorUtil.prependColorTag(Integer.toString(boosted), strColor)
							+ ColorUtil.prependColorTag("/" + base, Color.WHITE);
				}

				panelComponent.getChildren().add(LineComponent.builder()
						.left(skill.getName())
						.right(str)
						.rightColor(strColor)
						.build());
			}
		}

		return super.render(graphics);
	}

	private Color getTextColor(int boost)
	{
		if (boost < 0)
		{
			return new Color(238, 51, 51);
		}

		return boost <= config.boostThreshold() ? Color.YELLOW : Color.GREEN;
	}*/
}
