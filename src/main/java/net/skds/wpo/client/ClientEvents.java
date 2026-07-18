package net.skds.wpo.client;

import net.minecraft.client.Minecraft;
import net.minecraft.world.level.Level;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

public final class ClientEvents {

    private ClientEvents() {
    }

	public static void setup(final FMLClientSetupEvent event) {
	}

	public static Level currentLevel() {
		return Minecraft.getInstance().level;
	}
}
