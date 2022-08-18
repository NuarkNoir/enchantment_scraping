package xyz.nuark.enchantmentscraping.setup;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import xyz.nuark.enchantmentscraping.screen.ModMenuTypes;
import xyz.nuark.enchantmentscraping.screen.ScrapingStationScreen;

public class ClientSetup {
    public static void init(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(ModMenuTypes.SCRAPING_STATION_MENU.get(), ScrapingStationScreen::new);
        });
    }
}
