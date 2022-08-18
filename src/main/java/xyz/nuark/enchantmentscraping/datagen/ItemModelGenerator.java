package xyz.nuark.enchantmentscraping.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import xyz.nuark.enchantmentscraping.EnchantmentScraping;
import xyz.nuark.enchantmentscraping.item.ModItems;

public class ItemModelGenerator extends ItemModelProvider {
    public ItemModelGenerator(DataGenerator generator, ExistingFileHelper exFileHelper) {
        super(generator, EnchantmentScraping.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.IRON_SCRAPER_ITEM.get());
        basicItem(ModItems.DIAMOND_SCRAPER_ITEM.get());
        basicItem(ModItems.OBSIDIAN_SCRAPER_ITEM.get());
        basicItem(ModItems.NETHERITE_SCRAPER_ITEM.get());

        withExistingParent(ModItems.SCRAPING_STATION_ITEM.get().getRegistryName().getPath(), modLoc("block/scraping_station"));
    }
}
