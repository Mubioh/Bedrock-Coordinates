package me.mubioh.coordinates.client.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.player.PlayerEntity;

/**
 * A custom overlay that displays the player's current coordinates on the in-game HUD.
 * This class implements `HudRenderCallback` to draw on the HUD whenever it's rendered.
 */
@Environment(EnvType.CLIENT)
public class CoordinateOverlay implements HudRenderCallback {

    // Padding around the text in pixels
    private static final int PADDING = 2;
    // Horizontal offset from the left side of the screen
    private static final int OFFSET = 5;
    // Offset for the shadow to create a 3D text effect
    private static final int SHADOW_OFFSET = 1;

    /**
     * This method is called whenever the in-game HUD is rendered.
     * It draws the player's current coordinates on the screen.
     *
     * @param drawContext   The context used to draw elements on the HUD.
     * @param tickCounter   A counter tracking game ticks; unused in this case.
     */
    @Override
    public void onHudRender(DrawContext drawContext, RenderTickCounter tickCounter) {
        MinecraftClient client = MinecraftClient.getInstance();  // Get the Minecraft client instance
        PlayerEntity player = client.player;                     // Get the player entity

        // Ensure the player is not null (game must be in a playable state)
        if (player != null) {
            int scaledHeight = client.getWindow().getScaledHeight();   // Get the window height (scaled for GUI)
            int posY = scaledHeight / 10;                              // Position Y of the coordinates text (10% from the top)

            // Get the player's current coordinates, rounded to the nearest whole number
            int x = (int) Math.floor(player.getX());
            int y = (int) Math.floor(player.getY());
            int z = (int) Math.floor(player.getZ());
            String coordinates = String.format("Position: %d, %d, %d", x, y, z); // Format the coordinates into a string

            // Calculate text dimensions for proper padding and positioning
            int textWidth = client.textRenderer.getWidth(coordinates);   // Width of the text
            int textHeight = client.textRenderer.fontHeight;             // Height of the font

            // Draw a semi-transparent black background behind the text
            drawContext.fill(
                    0,
                    posY - PADDING,
                    OFFSET + textWidth + PADDING,
                    posY + textHeight + PADDING - SHADOW_OFFSET,
                    0x90000000  // Semi-transparent black
            );

            // Draw the coordinate text with a shadow for better visibility
            drawContext.drawTextWithShadow(client.textRenderer, coordinates, OFFSET, posY, 0xFFFFFF);
        }
    }
}
