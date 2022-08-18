package xyz.nuark.enchantmentscraping.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import xyz.nuark.enchantmentscraping.EnchantmentScraping;
import xyz.nuark.enchantmentscraping.block.ModBlocks;
import xyz.nuark.enchantmentscraping.item.ModItems;

public class LanguageGenerator extends LanguageProvider {
    public LanguageGenerator(DataGenerator gen, String locale) {
        super(gen, EnchantmentScraping.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        add("itemGroup." + EnchantmentScraping.MOD_ID, "Enchantment Scraping");

        add(ModBlocks.SCRAPING_STATION.get(), "Scraping Station");
        add(ModItems.IRON_SCRAPER_ITEM.get(), "Iron Scraper");
        add(ModItems.DIAMOND_SCRAPER_ITEM.get(), "Diamond Scraper");
        add(ModItems.OBSIDIAN_SCRAPER_ITEM.get(), "Obsidian Scraper");
        add(ModItems.NETHERITE_SCRAPER_ITEM.get(), "Netherite Scraper");
    }
}
