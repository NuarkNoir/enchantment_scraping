package xyz.nuark.enchantmentscraping.setup;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.jetbrains.annotations.NotNull;
import xyz.nuark.enchantmentscraping.EnchantmentScraping;
import xyz.nuark.enchantmentscraping.item.ModItems;

@Mod.EventBusSubscriber(modid = EnchantmentScraping.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModSetup {
    public static final CreativeModeTab ITEM_GROUP = new CreativeModeTab(EnchantmentScraping.MOD_ID) {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(ModItems.SCRAPING_STATION_ITEM.get());
        }
    };

    public static void setup() {
        IEventBus bus = MinecraftForge.EVENT_BUS;
    }

    public static void init(FMLCommonSetupEvent event) {

    }
}
