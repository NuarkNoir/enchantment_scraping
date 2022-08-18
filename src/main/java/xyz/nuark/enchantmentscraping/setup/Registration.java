package xyz.nuark.enchantmentscraping.setup;

import net.minecraftforge.eventbus.api.IEventBus;
import xyz.nuark.enchantmentscraping.block.ModBlocks;
import xyz.nuark.enchantmentscraping.block.entity.ModBlockEntities;
import xyz.nuark.enchantmentscraping.item.ModItems;
import xyz.nuark.enchantmentscraping.screen.ModMenuTypes;

public class Registration {
    public static void register(IEventBus eventBus) {
        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModBlockEntities.register(eventBus);
        ModMenuTypes.register(eventBus);
    }
}
