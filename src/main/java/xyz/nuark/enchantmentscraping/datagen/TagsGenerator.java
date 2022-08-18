package xyz.nuark.enchantmentscraping.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import xyz.nuark.enchantmentscraping.EnchantmentScraping;
import xyz.nuark.enchantmentscraping.ModTags;
import xyz.nuark.enchantmentscraping.item.ModItems;

public class TagsGenerator {
    public static class ItemTags extends ItemTagsProvider {
        public ItemTags(DataGenerator generator, BlockTagsProvider blockTagsProvider, @Nullable ExistingFileHelper existingFileHelper) {
            super(generator, blockTagsProvider, EnchantmentScraping.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags() {
            tag(ModTags.Items.SCRAPER_ITEM)
                    .add(ModItems.IRON_SCRAPER_ITEM.get())
                    .add(ModItems.DIAMOND_SCRAPER_ITEM.get())
                    .add(ModItems.OBSIDIAN_SCRAPER_ITEM.get())
                    .add(ModItems.NETHERITE_SCRAPER_ITEM.get());
        }
    }
    public static class BlockTags extends BlockTagsProvider {
        public BlockTags(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
            super(generator, EnchantmentScraping.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags() {
        }
    }
}
