package com.infernostats;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("BrewingConfig")
public interface BrewingConfig extends Config {
    enum InfoBoxState {
        ALWAYS, COMPLETION, PARTIAL_AND_COMPLETION
    }

    @ConfigItem(
            position = 0,
            keyName = "keldagrimInfobox",
            name = "Keldagrim Infobox",
            description = "Configures whether or not to show the Keldagrim infobox."
    )
    default boolean keldagrimInfobox() {
        return true;
    }

    @ConfigItem(
            position = 1,
            keyName = "phasmatysInfobox",
            name = "Phasmatys Infobox",
            description = "Configures whether or not to show the Phasmatys infobox."
    )
    default boolean phasmatysInfobox() {
        return true;
    }

    @ConfigItem(
            position = 2,
            keyName = "infoboxState",
            name = "Infobox State",
            description = "Infobox State"
    )
    default InfoBoxState infoboxState() {
        return InfoBoxState.COMPLETION;
    }
}