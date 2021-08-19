package com.infernostats;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.GameTick;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.events.RuneScapeProfileChanged;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.task.Schedule;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;

import javax.inject.Inject;
import java.awt.image.BufferedImage;
import java.time.temporal.ChronoUnit;

@Slf4j
@PluginDescriptor(
        name = "Brewing",
        description = "Brewing Overlay"
)
public class BrewingPlugin extends Plugin {
    BrewingInfoBox keldagrimInfoBox = null;
    BrewingInfoBox phasmatysInfoBox = null;

    private static final int BREWING_VAT_1_VARB = 736;
    private static final int BREWING_VAT_2_VARB = 737;
    private static final int THE_STUFF_VAT_1_VARB = 2294;
    private static final int THE_STUFF_VAT_2_VARB = 2295;

    private static final String CONFIG_GROUP = "BrewingConfig";
    private static final String KELDAGRIM_STATE_KEY = "keldagrimState";
    private static final String PHASMATYS_STATE_KEY = "phasmatysState";
    private static final String KELDAGRIM_STUFF_KEY = "keldagrimTheStuffAdded";
    private static final String PHASMATYS_STUFF_KEY = "phasmatysTheStuffAdded";
    private static final String KELDAGRIM_INFOBOX_KEY = "keldagrimInfobox";
    private static final String PHASMATYS_INFOBOX_KEY = "phasmatysInfobox";
    private static final String KELDAGRIM_SERVER_KEY = "keldagrimSentToServer";
    private static final String PHASMATYS_SERVER_KEY = "phasmatysSentToServer";
    private static final String INFOBOX_STATE_KEY = "infoboxState";

    @Inject
    private Client client;

    @Inject
    private BrewingConfig config;

    @Inject
    private ItemManager itemManager;

    @Inject
    private InfoBoxManager infoBoxManager;

    @Inject
    private ConfigManager configManager;

    @Inject
    public ClientThread clientThread;

    @Inject
    BrewingDataCollection manager;

    @Override
    protected void startUp() {
    }

    @Override
    protected void shutDown() {
        this.removeInfoBox(BrewingLocation.Keldagrim);
        this.removeInfoBox(BrewingLocation.Phasmatys);
    }

    @Provides
    BrewingConfig getConfig(ConfigManager configManager) {
        return configManager.getConfig(BrewingConfig.class);
    }

    @Subscribe
    public void onConfigChanged(ConfigChanged e)
    {
        if (!e.getGroup().equals(CONFIG_GROUP))
        {
            return;
        }

        switch (e.getKey()) {
            case KELDAGRIM_INFOBOX_KEY:
                if (shouldDisplayInfobox(BrewingLocation.Keldagrim)) {
                    this.addInfoBox(BrewingLocation.Keldagrim);
                } else {
                    this.removeInfoBox(BrewingLocation.Keldagrim);
                }
                break;
            case PHASMATYS_INFOBOX_KEY:
                if (shouldDisplayInfobox(BrewingLocation.Phasmatys)) {
                    this.addInfoBox(BrewingLocation.Phasmatys);
                } else {
                    this.removeInfoBox(BrewingLocation.Phasmatys);
                }
                break;
            case INFOBOX_STATE_KEY:
                if (shouldDisplayInfobox(BrewingLocation.Keldagrim)) {
                    this.addInfoBox(BrewingLocation.Keldagrim);
                } else {
                    this.removeInfoBox(BrewingLocation.Keldagrim);
                }

                if (shouldDisplayInfobox(BrewingLocation.Phasmatys)) {
                    this.addInfoBox(BrewingLocation.Phasmatys);
                } else {
                    this.removeInfoBox(BrewingLocation.Phasmatys);
                }
                break;
        }
    }

    @Subscribe
    public void onRuneScapeProfileChanged(RuneScapeProfileChanged e)
    {
        if (client.getGameState() != GameState.LOGIN_SCREEN)
        {
            this.removeInfoBox(BrewingLocation.Keldagrim);
            this.removeInfoBox(BrewingLocation.Phasmatys);

            if (client.getGameState() != GameState.HOPPING)
                return;
        }

        BufferedImage calquatKeg = itemManager.getImage(ItemID.CALQUAT_KEG);

        if (getConfigBrewingState(BrewingLocation.Keldagrim) == null)
        {
            setConfigBrewingState(BrewingLocation.Keldagrim, BrewingState.UNINITIALIZED);
        }

        if (getConfigBrewingState(BrewingLocation.Phasmatys) == null)
        {
            setConfigBrewingState(BrewingLocation.Phasmatys, BrewingState.UNINITIALIZED);
        }

        if (getConfigOpt(KELDAGRIM_SERVER_KEY, BrewingDataState.class) == null)
        {
            setConfigOpt(KELDAGRIM_SERVER_KEY, BrewingDataState.NOT_SENT);
        }

        if (getConfigOpt(PHASMATYS_SERVER_KEY, BrewingDataState.class) == null)
        {
            setConfigOpt(PHASMATYS_SERVER_KEY, BrewingDataState.NOT_SENT);
        }

        keldagrimInfoBox = new BrewingInfoBox(
                calquatKeg, this, BrewingLocation.Keldagrim,
                getConfigBrewingState(BrewingLocation.Keldagrim)
        );
        if (shouldDisplayInfobox(BrewingLocation.Keldagrim))
            this.addInfoBox(BrewingLocation.Keldagrim);

        phasmatysInfoBox = new BrewingInfoBox(
                calquatKeg, this, BrewingLocation.Phasmatys,
                getConfigBrewingState(BrewingLocation.Phasmatys)
        );
        if (shouldDisplayInfobox(BrewingLocation.Phasmatys))
            this.addInfoBox(BrewingLocation.Phasmatys);
    }

    @Subscribe
    public void onGameTick(GameTick e)
    {
        BrewingState oldKeldagrim = getConfigBrewingState(BrewingLocation.Keldagrim);
        BrewingState oldPhasmatys = getConfigBrewingState(BrewingLocation.Phasmatys);

        /* Update Fermenting Vat varbits */
        setConfigBrewingState(BrewingLocation.Keldagrim, BrewingState.fromInt(client.getVarbitValue(BREWING_VAT_1_VARB)));
        setConfigBrewingState(BrewingLocation.Phasmatys, BrewingState.fromInt(client.getVarbitValue(BREWING_VAT_2_VARB)));

        /* Update "The stuff" varbits */
        setConfigOpt(KELDAGRIM_STUFF_KEY, client.getVarbitValue(THE_STUFF_VAT_1_VARB) == 1);
        setConfigOpt(PHASMATYS_STUFF_KEY, client.getVarbitValue(THE_STUFF_VAT_2_VARB) == 1);

        /* Update info boxes with the latest brew state */
        updateInfoBox(BrewingLocation.Keldagrim);
        updateInfoBox(BrewingLocation.Phasmatys);

        if (getConfigBrewingState(BrewingLocation.Keldagrim) != oldKeldagrim)
        {
            if (oldKeldagrim.finished() && getConfigBrewingState(BrewingLocation.Keldagrim) == BrewingState.EMPTY)
            {
                setConfigOpt(KELDAGRIM_SERVER_KEY, BrewingDataState.NOT_SENT);
            }
        }

        if (getConfigBrewingState(BrewingLocation.Phasmatys) != oldPhasmatys)
        {
            if (oldPhasmatys.finished() && getConfigBrewingState(BrewingLocation.Phasmatys) == BrewingState.EMPTY)
            {
                setConfigOpt(PHASMATYS_SERVER_KEY, BrewingDataState.NOT_SENT);
            }
        }

        if (shouldSendData(BrewingLocation.Keldagrim) && getConfigBrewingState(BrewingLocation.Keldagrim).finished())
        {
            BrewingData data = new BrewingData(
                    BrewingLocation.Keldagrim,
                    getConfigBrewingState(BrewingLocation.Keldagrim),
                    (boolean) getConfigOpt(KELDAGRIM_STUFF_KEY, boolean.class),
                    client.getBoostedSkillLevels()[Skill.COOKING.ordinal()]
            );
            clientThread.invokeLater(() -> manager.storeEvent(data));
            setConfigOpt(KELDAGRIM_SERVER_KEY, BrewingDataState.SENT);
            manager.sendMessage("Keldagrim brewing data was sent to the server.");
        }

        if (shouldSendData(BrewingLocation.Phasmatys) && getConfigBrewingState(BrewingLocation.Phasmatys).finished())
        {
            BrewingData data = new BrewingData(
                    BrewingLocation.Phasmatys,
                    getConfigBrewingState(BrewingLocation.Phasmatys),
                    (boolean) getConfigOpt(PHASMATYS_STUFF_KEY, boolean.class),
                    client.getBoostedSkillLevels()[Skill.COOKING.ordinal()]
            );
            clientThread.invokeLater(() -> manager.storeEvent(data));
            setConfigOpt(PHASMATYS_SERVER_KEY, BrewingDataState.SENT);
            manager.sendMessage("Phasmatys brewing data was sent to the server.");
        }
    }

    private void updateInfoBox(BrewingLocation location)
    {
        switch (location)
        {
            case Keldagrim:
                keldagrimInfoBox.setBrewState(getConfigBrewingState(BrewingLocation.Keldagrim));
                if (shouldDisplayInfobox(BrewingLocation.Keldagrim))
                    this.addInfoBox(BrewingLocation.Keldagrim);
                else
                    this.removeInfoBox(BrewingLocation.Keldagrim);
                break;
            case Phasmatys:
                phasmatysInfoBox.setBrewState(getConfigBrewingState(BrewingLocation.Phasmatys));
                if (shouldDisplayInfobox(BrewingLocation.Phasmatys))
                    this.addInfoBox(BrewingLocation.Phasmatys);
                else
                    this.removeInfoBox(BrewingLocation.Phasmatys);
                break;
        }
    }

    private boolean shouldDisplayInfobox(BrewingLocation location)
    {
        switch (location)
        {
            case Keldagrim:
                if (!config.keldagrimInfobox())
                    return false;
                switch (config.infoboxState())
                {
                    case ALWAYS:
                        return true;
                    case COMPLETION:
                        return getConfigBrewingState(BrewingLocation.Keldagrim).finished();
                }
            case Phasmatys:
                if (!config.phasmatysInfobox())
                    return false;
                switch (config.infoboxState())
                {
                    case ALWAYS:
                        return true;
                    case COMPLETION:
                        return getConfigBrewingState(BrewingLocation.Phasmatys).finished();
                }
            default:
                return false;
        }
    }

    private void addInfoBox(BrewingLocation location)
    {
        if (location == BrewingLocation.Keldagrim && !keldagrimInfoBox.isVisible())
        {
            keldagrimInfoBox.setVisible(true);
            infoBoxManager.addInfoBox(keldagrimInfoBox);
        }

        if (location == BrewingLocation.Phasmatys && !phasmatysInfoBox.isVisible())
        {
            phasmatysInfoBox.setVisible(true);
            infoBoxManager.addInfoBox(phasmatysInfoBox);
        }
    }

    private void removeInfoBox(BrewingLocation location)
    {
        if (location == BrewingLocation.Keldagrim && keldagrimInfoBox.isVisible())
        {
            keldagrimInfoBox.setVisible(false);
            infoBoxManager.removeInfoBox(keldagrimInfoBox);
        }

        if (location == BrewingLocation.Phasmatys && phasmatysInfoBox.isVisible())
        {
            phasmatysInfoBox.setVisible(false);
            infoBoxManager.removeInfoBox(phasmatysInfoBox);
        }
    }

    private boolean shouldSendData()
    {
        if (!config.enableServer())
            return false;
        if (config.server() == null)
            return false;
        if (config.server().isEmpty())
            return false;
        return true;
    }

    private boolean shouldSendData(BrewingLocation location)
    {
        if (!config.enableServer())
            return false;
        if (config.server() == null)
            return false;
        if (config.server().isEmpty())
            return false;

        switch (location)
        {
            case Keldagrim:
                return getConfigOpt(KELDAGRIM_SERVER_KEY, BrewingDataState.class) == BrewingDataState.NOT_SENT;
            case Phasmatys:
                return getConfigOpt(PHASMATYS_SERVER_KEY, BrewingDataState.class) == BrewingDataState.NOT_SENT;
            default:
                return false;
        }
    }

    @Schedule(
            period = 5,
            unit = ChronoUnit.SECONDS,
            asynchronous = true
    )
    public void submitToAPI()
    {
        if (!shouldSendData())
            return;
        manager.submitToAPI(config.server());
    }

    private BrewingState getConfigBrewingState(BrewingLocation location)
    {
        switch (location)
        {
            case Keldagrim:
                return (BrewingState) getConfigOpt(KELDAGRIM_STATE_KEY, BrewingState.class);
            case Phasmatys:
                return (BrewingState) getConfigOpt(PHASMATYS_STATE_KEY, BrewingState.class);
        }
        return BrewingState.UNINITIALIZED;
    }

    private void setConfigBrewingState(BrewingLocation location, BrewingState state)
    {
        if (location == BrewingLocation.Keldagrim)
        {
            setConfigOpt(KELDAGRIM_STATE_KEY, state);
        }
        else if (location == BrewingLocation.Phasmatys)
        {
            setConfigOpt(PHASMATYS_STATE_KEY, state);
        }
    }

    private Object getConfigOpt(String key, Class<?> clazz)
    {
        return configManager.getRSProfileConfiguration(CONFIG_GROUP, key, clazz);
    }

    private void setConfigOpt(String key, Object obj)
    {
        configManager.setRSProfileConfiguration(CONFIG_GROUP, key, obj);
    }
}