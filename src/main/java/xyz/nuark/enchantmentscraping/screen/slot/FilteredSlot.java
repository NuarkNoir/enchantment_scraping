package xyz.nuark.enchantmentscraping.screen.slot;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public class FilteredSlot extends SlotItemHandler {
    Predicate<ItemStack> filter;

    public FilteredSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition, Predicate<ItemStack> filter) {
        super(itemHandler, index, xPosition, yPosition);
        this.filter = filter;
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        return filter.test(stack);
    }
}
