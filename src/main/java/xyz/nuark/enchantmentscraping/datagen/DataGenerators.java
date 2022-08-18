package xyz.nuark.enchantmentscraping.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import xyz.nuark.enchantmentscraping.EnchantmentScraping;

@Mod.EventBusSubscriber(modid = EnchantmentScraping.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        if (event.includeServer()) {
            generator.addProvider(new RecipesGenerator(generator));
            var blockTags = new TagsGenerator.BlockTags(generator, event.getExistingFileHelper());
            generator.addProvider(blockTags);
            generator.addProvider(new TagsGenerator.ItemTags(generator, blockTags, event.getExistingFileHelper()));
        }
        if (event.includeClient()) {
            generator.addProvider(new BlockStatesGenerator(generator, event.getExistingFileHelper()));
            generator.addProvider(new ItemModelGenerator(generator, event.getExistingFileHelper()));
            generator.addProvider(new LanguageGenerator(generator, "en_us"));
        }
    }
}
