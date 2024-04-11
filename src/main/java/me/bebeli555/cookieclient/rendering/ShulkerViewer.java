package me.bebeli555.cookieclient.rendering;

import me.bebeli555.cookieclient.Mod;
import me.bebeli555.cookieclient.gui.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import java.awt.*;

public class ShulkerViewer extends Mod {
    public static Setting gamemechanics = new Setting(Mode.BOOLEAN, "Anchor", 70, "AnchorSpeed");
    public static Minecraft mc;
    public ColorSetting outlineColor = new ColorSetting("Outline", new GSColor(255, 0, 0, 255));
    public ColorSetting fillColor = new ColorSetting("Fill", new GSColor(0, 0, 0, 255));
    public ShulkerViewer() {
        super(Group.GAMEMECHANICS, "ShulkerViewer", "Anchor");
    }
    public void renderShulkerPreview(ItemStack itemStack, int posX, int posY, int width, int height) {
        GSColor outline = new GSColor(outlineColor.getValue(), 255);
        GSColor fill = new GSColor(fillColor.getValue(), 200);
        GlStateManager.disableDepth();
        GlStateManager.enableDepth();

        NonNullList<ItemStack> contentItems = NonNullList.withSize(27, ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(itemStack.getTagCompound().getCompoundTag("BlockEntityTag"), contentItems);

        for (int i = 0; i < contentItems.size(); i++) {
            int finalX = posX + 1 + i % 9 * 18;
            int finalY = posY + 31 + (i / 9 - 1) * 18;
        }
    }
}
