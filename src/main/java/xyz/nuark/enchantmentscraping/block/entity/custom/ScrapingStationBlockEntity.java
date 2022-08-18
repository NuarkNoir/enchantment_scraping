package xyz.nuark.enchantmentscraping.block.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.nuark.enchantmentscraping.EnchantmentScraping;
import xyz.nuark.enchantmentscraping.block.entity.ModBlockEntities;
import xyz.nuark.enchantmentscraping.item.custom.ScraperItem;
import xyz.nuark.enchantmentscraping.screen.ScrapingStationMenu;

import java.util.Random;

public class ScrapingStationBlockEntity extends BlockEntity implements MenuProvider {
    private final int CRAFTING_TIME = 200;
    private int craftingTicker = 0;
    private boolean isCrafting = false;
    private final Random random = new Random();

    protected final ContainerData dataAccess = new ContainerData() {
        public int get(int idx) {
            return switch (idx) {
                case 0 -> ScrapingStationBlockEntity.this.craftingTicker;
                default -> 0;
            };
        }

        public void set(int idx, int value) {
            switch (idx) {
                case 0 -> ScrapingStationBlockEntity.this.craftingTicker = value;
            }
        }

        public int getCount() {
            return 1;
        }
    };

    private final ItemStackHandler itemHandler = new ItemStackHandler(4) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            updateCraftingState();
        }
    };

    private LazyOptional<ItemStackHandler> lazyItemHandler = LazyOptional.empty();

    public ScrapingStationBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.SCRAPING_STATION_BLOCK_ENTITY.get(), pos, blockState);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return new TranslatableComponent("block.enchantmentscraping.scraping_station");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerID, Inventory inventory, Player player) {
        return new ScrapingStationMenu(containerID, inventory, this, dataAccess);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @javax.annotation.Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        tag.putBoolean("isCrafting", isCrafting);
        tag.putInt("craftingTicker", craftingTicker);
        super.saveAdditional(tag);
    }

    @Override
    public void load(@NotNull CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        isCrafting = nbt.getBoolean("isCrafting");
        craftingTicker = nbt.getInt("craftingTicker");
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        if (this.level != null) {
            Containers.dropContents(this.level, this.worldPosition, inventory);
        } else {
            EnchantmentScraping.LOGGER.error("ScrapingStationBlockEntity.drops() - level is null");
        }
    }

    private void updateCraftingState() {
        var item = itemHandler.getStackInSlot(0);
        var books = itemHandler.getStackInSlot(1);
        var scraper = itemHandler.getStackInSlot(2);
        var result = itemHandler.getStackInSlot(3);

        isCrafting = !item.isEmpty() && !books.isEmpty() && !scraper.isEmpty() && result.isEmpty();
        craftingTicker = 0;
    }

    public void tickServer() {
        if (!isCrafting) return;
        craftingTicker++;
        if (craftingTicker >= CRAFTING_TIME) {
            craftingTicker = 0;

            var enchantments = EnchantmentHelper.getEnchantments(itemHandler.getStackInSlot(0));
            if (!enchantments.isEmpty()) {
                var scraperChance = ((ScraperItem) itemHandler.getStackInSlot(2).getItem()).getScrapingChance();
                var applicableEnchantments = enchantments.entrySet().stream().filter(entry -> random.nextFloat(1) < scraperChance).map(e -> new EnchantmentInstance(e.getKey(), e.getValue())).toList();
                if (applicableEnchantments.isEmpty()) {
                    itemHandler.setStackInSlot(3, new ItemStack(Items.BOOK));
                } else {
                    var enchantedBook = new ItemStack(Items.ENCHANTED_BOOK);
                    applicableEnchantments.forEach(e -> EnchantedBookItem.addEnchantment(enchantedBook, e));
                    itemHandler.setStackInSlot(3, enchantedBook);
                }
                itemHandler.getStackInSlot(2).setDamageValue(itemHandler.getStackInSlot(2).getDamageValue() + 1);
                itemHandler.getStackInSlot(1).setCount(itemHandler.getStackInSlot(1).getCount() - 1);
                itemHandler.getStackInSlot(0).getEnchantmentTags().clear();
            }
        }

        setChanged();
    }
}
