package xyz.nuark.enchantmentscraping.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import xyz.nuark.enchantmentscraping.EnchantmentScraping;
import xyz.nuark.enchantmentscraping.block.custom.ScrapingStationBlock;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, EnchantmentScraping.MOD_ID);

     public static final RegistryObject<Block> SCRAPING_STATION = BLOCKS.register("scraping_station", ScrapingStationBlock::new);
//    public static final RegistryObject<Block> SCRAPING_STATION = BLOCKS.register("scraping_station", ()->new Block(BlockBehaviour.Properties.of(Material.STONE)));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
