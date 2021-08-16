package com.infernostats;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

@ConfigGroup("BrewingConfig")
public interface BrewingConfig extends Config {
    enum InfoBoxState {
        COMPLETION, ALWAYS;
    }

    @ConfigSection(
            name = "Infobox",
            description = "Infobox",
            position = 0
    )
    String infobox = "infobox";

    @ConfigItem(
            position = 0,
            keyName = "keldagrimInfobox",
            name = "Keldagrim Infobox",
            description = "Keldagrim Infobox",
            section = infobox
    )
    default boolean keldagrimInfobox() {
        return true;
    }

    @ConfigItem(
            position = 1,
            keyName = "phasmatysInfobox",
            name = "Phasmatys Infobox",
            description = "Phasmatys Infobox",
            section = infobox
    )
    default boolean phasmatysInfobox() {
        return true;
    }

    @ConfigItem(
            position = 2,
            keyName = "infoboxState",
            name = "Infobox State",
            description = "Infobox State",
            section = infobox
    )
    default InfoBoxState infoboxState() {
        return InfoBoxState.COMPLETION;
    }

    @ConfigSection(
            name = "Server",
            description = "Server",
            position = 1
    )
    String server = "server";

    @ConfigItem(
            position = 0,
            keyName = "sendServerData",
            name = "Send Data to Server",
            description = "Send Brew Data to Server",
            section = server
    )
    default boolean enableServer() {
        return false;
    }

    @ConfigItem(
            position = 1,
            keyName = "server",
            name = "Server URL",
            description = "Server URL",
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