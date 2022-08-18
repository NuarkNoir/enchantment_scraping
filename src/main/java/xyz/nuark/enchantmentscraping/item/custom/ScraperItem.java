package xyz.nuark.enchantmentscraping.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import org.jetbrains.annotations.NotNull;
import xyz.nuark.enchantmentscraping.setup.ModSetup;

public class ScraperItem extends Item {
    private final float scrapingChance;

    public ScraperItem(int durability, Rarity rarity, float scrapingChance) {
        super(new Item.Properties().tab(ModSetup.ITEM_GROUP).durability(durability).rarity(rarity));
        this.scrapingChance = scrapingChance;
    }

    public float getScrapingChance() {
        return scrapingChance;
    }

    @Override
    public @NotNull Component getDescription() {
        return new TextComponent("Scraping chance: " + (int)(scrapingChance * 100) + "%");
    }
}
