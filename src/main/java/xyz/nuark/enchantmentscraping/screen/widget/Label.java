package xyz.nuark.enchantmentscraping.screen.widget;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.Widget;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class Label implements Widget {
    private Component text;
    private final int x;
    private final int y;
    private final int color;
    private final boolean centered;

    public Label(Component p_120736_, int p_120737_, int p_120738_, int p_120739_, boolean centered) {
        this.text = p_120736_.copy();
        this.x = p_120737_;
        this.y = p_120738_;
        this.color = p_120739_;
        this.centered = centered;
    }

    public void render(@NotNull PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        if (centered) {
            GuiComponent.drawCenteredString(poseStack, Minecraft.getInstance().font, this.text, this.x, this.y, this.color);
        } else {
            GuiComponent.drawString(poseStack, Minecraft.getInstance().font, this.text, this.x, this.y, this.color);
        }
    }

    public void setText(Component p_175041_) {
        this.text = p_175041_;
    }

    public Component getText() {
        return this.text;
    }
}
