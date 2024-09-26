package com.brewing;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup(BrewingConfig.GROUP)
public interface BrewingConfig extends Config {
	String GROUP = "BrewingConfig";

	enum DisplayMode {
		KELDAGRIM,
		PORT_PHASMATYS,
		ALDARIN,
		ALL
	}

	enum VatState {
		ALWAYS,
		COMPLETION,
		ANY_CONTENTS
	}

	enum BarrelState {
		ALWAYS,
		FULL,
		NOT_EMPTY
	}

	@ConfigItem(
		keyName = "vatDisplay",
		name = "Display Vats",
		description = "Configures which vats to display",
		position = 1
	)
	default DisplayMode displayVats() {
		return DisplayMode.ALL;
	}

	@ConfigItem(
		keyName = "vatDisplaySetting",
		name = "Vat Display Condition",
		description = "Configures when vats be displayed",
		position = 2
	)
	default VatState vatDisplayCond() {
		return VatState.COMPLETION;
	}

	@ConfigItem(
		keyName = "barrelDisplay",
		name = "Display Barrels",
		description = "Configures which barrels to display",
		position = 3
	)
	default DisplayMode displayBarrels() {
		return DisplayMode.ALL;
	}

	@ConfigItem(
		keyName = "barrelDisplaySetting",
		name = "Barrel Display Condition",
		description = "Configures when barrels be displayed",
		position = 4
	)
	default BarrelState barrelDisplayCond() {
		return BarrelState.NOT_EMPTY;
	}

	@ConfigItem(
		keyName = "notifySetting",
		name = "Notify On Completion",
		description = "Configures if to notify when a vat is done",
		position = 5
	)
	default boolean notifyOnCompletion() {
		return false;
	}

	@ConfigItem(
		keyName = "chatMessage",
		name = "Chat Message On Completion",
		description = "Show a message when you can collect your brews.",
		position = 6
	)
	default boolean chatMessageOnCompletion() {
		return false;
	}
}