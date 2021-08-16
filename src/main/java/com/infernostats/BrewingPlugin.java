package com.infernostats;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.GameTick;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
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
    private static final String KELDAGRIM_INFOBOX_KEY = "keldagrimInfobox";
    private static final String PHASMATYS_INFOBOX_KEY = "phasmatysInfobox";
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
    public ClientThread clientThread;

    @Inject
    BrewingDataCollection manager;

    @Override
    protected void startUp() {
        BufferedImage calquatKeg = itemManager.getImage(ItemID.CALQUAT_KEG);

        if (config.keldagrimState() == null) {
            config.keldagrimState(BrewingState.UNINITIALIZED);
        }

        if (config.phasmatysState() == null) {
            config.phasmatysState(BrewingState.UNINITIALIZED);
        }

        if (config.keldagrimSentToServer() == null) {
            config.keldagrimSentToServer(BrewingDataState.NOT_SENT);
        }

        if (config.phasmatysSentToServer() == null) {
            config.phasmatysSentToServer(BrewingDataState.NOT_SENT);
        }

        keldagrimInfoBox = new BrewingInfoBox(this, config, calquatKeg, BrewingLocation.Keldagrim, config.keldagrimState());
        if (shouldDisplayInfobox(BrewingLocation.Keldagrim))
            this.addInfoBox(BrewingLocation.Keldagrim);

        phasmatysInfoBox = new BrewingInfoBox(this, config, calquatKeg, BrewingLocation.Phasmatys, config.phasmatysState());
        if (shouldDisplayInfobox(BrewingLocation.Phasmatys))
            this.addInfoBox(BrewingLocation.Phasmatys);
    }

    @Override
    protected void shutDown() {
        infoBoxManager.removeIf(BrewingInfoBox.class::isInstance);
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

        String key = e.getKey();

        if (key.equals(KELDAGRIM_INFOBOX_KEY))
        {
            if (shouldDisplayInfobox(BrewingLocation.Keldagrim))
            {
                this.addInfoBox(BrewingLocation.Keldagrim);
            }
            else
            {
                this.removeInfoBox(BrewingLocation.Keldagrim);
            }
        }

        else if (key.equals(PHASMATYS_INFOBOX_KEY))
        {
            if (shouldDisplayInfobox(BrewingLocation.Phasmatys))
            {
                this.addInfoBox(BrewingLocation.Phasmatys);
            }
            else
            {
                this.removeInfoBox(BrewingLocation.Phasmatys);
            }
        }

        else if (key.equals(INFOBOX_STATE_KEY))
        {
            if (shouldDisplayInfobox(BrewingLocation.Keldagrim))
            {
                this.addInfoBox(BrewingLocation.Keldagrim);
            }
            else
            {
                this.removeInfoBox(BrewingLocation.Keldagrim);
            }

            if (shouldDisplayInfobox(BrewingLocation.Phasmatys))
            {
                this.addInfoBox(BrewingLocation.Phasmatys);
            }
            else
            {
                this.removeInfoBox(BrewingLocation.Phasmatys);
            }
        }
    }

    @Subscribe
    public void onGameTick(GameTick e)
    {
        BrewingState oldKeldagrim = config.keldagrimState();
        BrewingState oldPhasmatys = config.phasmatysState();

        /* Update Fermenting Vat varbits */
        config.keldagrimState(BrewingState.fromInt(client.getVarbitValue(BREWING_VAT_1_VARB)));
        config.phasmatysState(BrewingState.fromInt(client.getVarbitValue(BREWING_VAT_2_VARB)));

        /* Update "The stuff" varbits */
        config.keldagrimTheStuffAdded(client.getVarbitValue(THE_STUFF_VAT_1_VARB) == 1);
        config.phasmatysTheStuffAdded(client.getVarbitValue(THE_STUFF_VAT_2_VARB) == 1);

        /* Update info boxes with the latest brew state */
        updateInfoBox(BrewingLocation.Keldagrim);
        updateInfoBox(BrewingLocation.Phasmatys);

        if (config.keldagrimState() != oldKeldagrim)
        {
            if (oldKeldagrim.finished() && config.keldagrimState() == BrewingState.EMPTY)
            {
                config.keldagrimSentToServer(BrewingDataState.NOT_SENT);
            }
        }

        if (config.phasmatysState() != oldPhasmatys)
        {
            if (oldPhasmatys.finished() && config.phasmatysState() == BrewingState.EMPTY)
            {
                config.phasmatysSentToServer(BrewingDataState.NOT_SENT);
            }
        }

        if (shouldSendData(BrewingLocation.Keldagrim) && config.keldagrimState().finished())
        {
            BrewingData data = new BrewingData(
                    BrewingLocation.Keldagrim,
                    config.keldagrimState(),
                    config.keldagrimTheStuffAdded()
            );
            clientThread.invokeLater(() -> manager.storeEvent(data));
            config.keldagrimSentToServer(BrewingDataState.SENT);
        }

        if (shouldSendData(BrewingLocation.Phasmatys) && config.phasmatysState().finished())
        {
            BrewingData data = new BrewingData(
                    BrewingLocation.Phasmatys,
                    config.phasmatysState(),
                    config.phasmatysTheStuffAdded()
            );
            clientThread.invokeLater(() -> manager.storeEvent(data));
            config.phasmatysSentToServer(BrewingDataState.SENT);
        }
    }

    private void updateInfoBox(BrewingLocation location)
    {
        switch (location)
        {
            case Keldagrim:
                keldagrimInfoBox.setBrewState(config.keldagrimState());
                if (shouldDisplayInfobox(BrewingLocation.Keldagrim))
                    this.addInfoBox(BrewingLocation.Keldagrim);
                else
                    this.removeInfoBox(BrewingLocation.Keldagrim);
                break;
            case Phasmatys:
                phasmatysInfoBox.setBrewState(config.phasmatysState());
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
                        return config.keldagrimState().finished();
                }
            case Phasmatys:
                if (!config.phasmatysInfobox())
                    return false;
                switch (config.infoboxState())
                {
                    case ALWAYS:
                        return true;
                    case COMPLETION:
                        return config.phasmatysState().finished();
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
                return config.keldagrimSentToServer() == BrewingDataState.NOT_SENT;
            case Phasmatys:
                return config.phasmatysSentToServer() == BrewingDataState.NOT_SENT;
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
}