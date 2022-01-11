package com.infernostats;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

@ConfigGroup("BrewingConfig")
public interface BrewingConfig extends Config {
    @ConfigSection(
            name = "Infobox",
            description = "Settings relating to infoboxes",
            position = 0
    )
    String infobox = "infobox";

    @ConfigItem(
            position = 0,
            keyName = "keldagrimInfobox",
            name = "Keldagrim Infobox",
            description = "Configures whether to show the Keldagrim infobox.",
            section = infobox
    )
    default boolean keldagrimInfobox() {
        return true;
    }

    @ConfigItem(
            position = 1,
            keyName = "phasmatysInfobox",
            name = "Phasmatys Infobox",
            description = "Configures whether to show the Phasmatys infobox.",
            section = infobox
    )
    default boolean phasmatysInfobox() {
        return true;
    }

    @ConfigItem(
            position = 2,
            keyName = "infoboxState",
            name = "Infobox State",
            description = "Configures when to show the infoboxes",
            section = infobox
    )
    default InfoBoxState infoboxState() {
        return InfoBoxState.PREP_OR_COMPLETED;
    }

    @ConfigSection(
            name = "Server",
            description = "Settings relating to the server",
            position = 1
    )
    String server = "server";

    @ConfigItem(
            position = 0,
            keyName = "sendServerData",
            name = "Send Data to Server",
            description = "Configures whether to send brewing data to server.",
            section = server
    )
    default boolean enableServer() {
        return false;
    }

    @ConfigItem(
            position = 1,
            keyName = "server",
            name = "Server URL",
            description = "Configures which URL to send the brewing data to.",
            section = server
    )
    default String server() {
        return "http://ec2-52-205-10-246.compute-1.amazonaws.com/";
    }

    @ConfigItem(
            keyName = "keldagrimState",
            name = "",
            description = "",
            hidden = true
    )
    BrewingState keldagrimState();

    @ConfigItem(
            keyName = "keldagrimState",
            name = "",
            description = ""
    )
    void keldagrimState(BrewingState keldagrimState);

    @ConfigItem(
            keyName = "phasmatysState",
            name = "",
            description = "",
            hidden = true
    )
    BrewingState phasmatysState();

    @ConfigItem(
            keyName = "phasmatysState",
            name = "",
            description = ""
    )
    void phasmatysState(BrewingState phasmatysState);

    @ConfigItem(
            keyName = "keldagrimTheStuffAdded",
            name = "",
            description = "",
            hidden = true
    )
    boolean keldagrimTheStuffAdded();

    @ConfigItem(
            keyName = "keldagrimTheStuffAdded",
            name = "",
            description = ""
    )
    void keldagrimTheStuffAdded(boolean theStuffAdded);

    @ConfigItem(
            keyName = "phasmatysTheStuffAdded",
            name = "",
            description = "",
            hidden = true
    )
    boolean phasmatysTheStuffAdded();

    @ConfigItem(
            keyName = "phasmatysTheStuffAdded",
            name = "",
            description = ""
    )
    void phasmatysTheStuffAdded(boolean theStuffAdded);

    @ConfigItem(
            keyName = "keldagrimSentToServer",
            name = "",
            description = "",
            hidden = true
    )
    BrewingDataState keldagrimSentToServer();

    @ConfigItem(
            keyName = "keldagrimSentToServer",
            name = "",
            description = ""
    )
    void keldagrimSentToServer(BrewingDataState sentToServer);

    @ConfigItem(
            keyName = "phasmatysSentToServer",
            name = "",
            description = "",
            hidden = true
    )
    BrewingDataState phasmatysSentToServer();

    @ConfigItem(
            keyName = "phasmatysSentToServer",
            name = "",
            description = ""
    )
    void phasmatysSentToServer(BrewingDataState sentToServer);
}