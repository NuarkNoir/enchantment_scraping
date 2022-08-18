package xyz.nuark.enchantmentscraping.datagen;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;
import xyz.nuark.enchantmentscraping.block.ModBlocks;
import xyz.nuark.enchantmentscraping.item.ModItems;

import java.util.function.Consumer;

public class RecipesGenerator extends RecipeProvider {
    public RecipesGenerator(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(ModBlocks.SCRAPING_STATION.get())
                .pattern("s s")
                .pattern("sos")
                .pattern("lll")
                .define('s', Items.STICK)
                .define('o', Tags.Items.OBSIDIAN)
                .define('l', Items.SMOOTH_QUARTZ_SLAB)
                .unlockedBy("created_enchanting_table", InventoryChangeTrigger.TriggerInstance.hasItems(Items.ENCHANTING_TABLE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.IRON_SCRAPER_ITEM.get())
                .pattern(" m ")
                .pattern("msm")
                .pattern(" m ")
                .define('m', Items.IRON_INGOT)
                .define('s', Items.STICK)
                .unlockedBy("created_scraper", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.SCRAPING_STATION.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.DIAMOND_SCRAPER_ITEM.get())
                .pattern(" m ")
                .pattern("msm")
                .pattern(" m ")
                .define('m', Items.DIAMOND)
                .define('s', Items.STICK)
                .unlockedBy("created_scraper", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.SCRAPING_STATION.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.OBSIDIAN_SCRAPER_ITEM.get())
                .pattern(" m ")
                .pattern("msm")
                .pattern(" m ")
                .define('m', Items.OBSIDIAN)
                .define('s', Items.STICK)
                .unlockedBy("created_scraper", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.SCRAPING_STATION.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.NETHERITE_SCRAPER_ITEM.get())
                .pattern(" m ")
                .pattern("msm")
                .pattern(" m ")
                .define('m', Items.NETHERITE_SCRAP)
                .define('s', Items.STICK)
                .unlockedBy("created_scraper", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.SCRAPING_STATION.get()))
                .save(consumer);
    }
}
