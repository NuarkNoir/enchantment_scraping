package xyz.nuark.enchantmentscraping;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags {
    public static class Items {
        public static final TagKey<Item> SCRAPER_ITEM = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(EnchantmentScraping.MOD_ID, "scraper_item"));
    }
}
