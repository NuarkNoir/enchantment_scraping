package xyz.nuark.enchantmentscraping.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import xyz.nuark.enchantmentscraping.EnchantmentScraping;
import xyz.nuark.enchantmentscraping.block.ModBlocks;

public class BlockStatesGenerator extends BlockStateProvider {
    public BlockStatesGenerator(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, EnchantmentScraping.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

    }
}
