/**
 * 	made for entertainment purposes only
 * 	not intended to offend anyone
 * 	@author: deadlyPig
 */

package ru.matt;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matt.api.discord.DiscordSS;
import ru.matt.api.modmenu.HaramConfig;
import ru.matt.api.modmenu.HaramScreen;
import ru.matt.haram.Kill;
import ru.matt.haram.Koran;
import ru.matt.haram.PigEat;

public class HaramClient implements ModInitializer {
	public static KeyBinding config;
	public static final Logger LOGGER = LoggerFactory.getLogger("haramclient");

	@Override
	public void onInitialize() {
		// Load Config
		HaramConfig.load();
		// Initialize class PigEat
		new PigEat();
		// Initialize class Kill
		new Kill();
		// Initialize class Koran
		CommandRegistrationCallback.EVENT.register((dispatcher, dedicated, player) -> {
			Koran.register();
		});
		// Initialize start DiscordRPC
		DiscordSS.init();
		// Registered bind for opening config
		config = KeyBindingHelper.registerKeyBinding(new KeyBinding("haramclient.key", GLFW.GLFW_KEY_UNKNOWN, "HaramClient"));
		ClientTickEvents.END_CLIENT_TICK.register(mc -> {
			if (config.wasPressed()) {
				mc.setScreen(new HaramScreen(null));
			}
		});
	}
}