package xyz.nuark.enchantmentscraping.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import xyz.nuark.enchantmentscraping.EnchantmentScraping;
import xyz.nuark.enchantmentscraping.block.ModBlocks;
import xyz.nuark.enchantmentscraping.block.entity.custom.ScrapingStationBlockEntity;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, EnchantmentScraping.MOD_ID);

    public static final RegistryObject<BlockEntityType<ScrapingStationBlockEntity>> SCRAPING_STATION_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("scraping_station_block_entity", () -> BlockEntityType.Builder.of(ScrapingStationBlockEntity::new, ModBlocks.SCRAPING_STATION.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
