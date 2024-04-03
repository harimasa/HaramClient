/**
 * 	made for entertainment purposes only
 * 	not intended to offend anyone
 * 	@author: Mattgyve1990 aka HeMatik
 */

package ru.matt;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matt.haram.Kill;
import ru.matt.haram.Koran;
import ru.matt.haram.PigEat;

public class HaramClient implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("haramclient");

	@Override
	public void onInitialize() {
		// Initialize class PigEat
		new PigEat();
		// Initialize class Kill
		new Kill();
		// Initialize class Koran
		CommandRegistrationCallback.EVENT.register((dispatcher, dedicated, player) -> {
			Koran.register(dispatcher);
		});
	}
}