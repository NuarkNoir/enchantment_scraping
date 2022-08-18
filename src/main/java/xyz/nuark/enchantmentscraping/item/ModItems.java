package xyz.nuark.enchantmentscraping.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import xyz.nuark.enchantmentscraping.EnchantmentScraping;
import xyz.nuark.enchantmentscraping.block.ModBlocks;
import xyz.nuark.enchantmentscraping.item.custom.ScraperItem;
import xyz.nuark.enchantmentscraping.setup.ModSetup;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, EnchantmentScraping.MOD_ID);

    public static final RegistryObject<Item> SCRAPING_STATION_ITEM = fromBlock(ModBlocks.SCRAPING_STATION);
    public static final RegistryObject<Item> IRON_SCRAPER_ITEM = createScraper("iron_scraper", 5, Rarity.COMMON, 0.25f);
    public static final RegistryObject<Item> DIAMOND_SCRAPER_ITEM = createScraper("diamond_scraper", 40, Rarity.UNCOMMON, 0.4f);
    public static final RegistryObject<Item> OBSIDIAN_SCRAPER_ITEM = createScraper("obsidian_scraper", 60, Rarity.RARE, 0.55f);
    public static final RegistryObject<Item> NETHERITE_SCRAPER_ITEM = createScraper("netherite_scraper", 120, Rarity.EPIC, 0.7f);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().tab(ModSetup.ITEM_GROUP)));
    }

    public static <I extends Item> RegistryObject<Item> createScraper(String name, int durability, Rarity rarity, float scrapingChance) {
        return ITEMS.register(name, () -> new ScraperItem(durability, rarity, scrapingChance));
    }
}
