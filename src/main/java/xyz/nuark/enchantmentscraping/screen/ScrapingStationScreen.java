package xyz.nuark.enchantmentscraping.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import org.apache.commons.compress.utils.Lists;
import org.jetbrains.annotations.NotNull;
import xyz.nuark.enchantmentscraping.EnchantmentScraping;
import xyz.nuark.enchantmentscraping.ModTags;
import xyz.nuark.enchantmentscraping.item.custom.ScraperItem;

import java.util.List;
import java.util.Optional;

public class ScrapingStationScreen extends AbstractContainerScreen<ScrapingStationMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(EnchantmentScraping.MOD_ID, "textures/gui/scraping_station_gui.png");

    public ScrapingStationScreen(ScrapingStationMenu scrapingStationMenu, Inventory inventory, Component title) {
        super(scrapingStationMenu, inventory, title);

        imageWidth = 176;
        imageHeight = 166;
    }

    @Override
    protected void renderBg(@NotNull PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight);

        int progress = (int) (40f * ((float) menu.getCraftingTicker() / 200f));
        if (progress > 0) {
            this.blit(pPoseStack, x + 77, y + 33, 176, 0, progress, 18);
        }
    }

    @Override
    protected void renderLabels(@NotNull PoseStack pPoseStack, int pMouseX, int pMouseY) {
        this.font.draw(pPoseStack, this.title, (float) this.titleLabelX, (float) this.titleLabelY, 4210752);
    }

    @Override
    public void render(@NotNull PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, mouseX, mouseY, delta);
        if (this.menu.getCarried().isEmpty() && this.hoveredSlot != null && hoveredSlot.hasItem() && hoveredSlot.getItem().is(ModTags.Items.SCRAPER_ITEM)) {
            List<Component> componentList = getTooltipFromItem(hoveredSlot.getItem());
            componentList.add(hoveredSlot.getItem().getItem().getDescription());
            renderTooltip(pPoseStack, componentList, Optional.empty(), mouseX, mouseY);
        } else {
            renderTooltip(pPoseStack, mouseX, mouseY);
        }
    }
}
