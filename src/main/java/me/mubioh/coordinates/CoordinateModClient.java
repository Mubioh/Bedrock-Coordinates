package me.mubioh.coordinates;

import me.mubioh.coordinates.client.gui.CoordinateOverlay;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

/**
 * The client-side initializer class for the Coordinate Mod.
 * Implements the `ClientModInitializer` interface to set up client-side logic when the mod is loaded.
 */
public class CoordinateModClient implements ClientModInitializer {

    /**
     * Called to initialize the client-side of the mod during game startup.
     * Registers a custom overlay for the in-game HUD that will display player coordinates.
     */
    @Override
    public void onInitializeClient() {
        // Registers the custom HUD overlay (CoordinateOverlay) to be drawn on the in-game HUD.
        HudRenderCallback.EVENT.register(new CoordinateOverlay());
    }

}
