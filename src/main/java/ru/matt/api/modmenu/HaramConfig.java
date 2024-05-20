package ru.matt.api.modmenu;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import ru.matt.HaramClient;

import java.lang.reflect.Modifier;
import java.nio.file.Files;

public class HaramConfig {
    public static final transient Gson GSON = new GsonBuilder().excludeFieldsWithModifiers(Modifier.TRANSIENT).create();

    // Enabled haram eat?
    public static boolean enabledEat = true;

    // Enabled Kill Spam?
    public static boolean enabledKill = false;

    // Load Config
    public static void load() {
        try {
            var path = FabricLoader.getInstance().getConfigDir().resolve("haramclient.json");
            if (Files.exists(path)) {
                GSON.fromJson(Files.readString(path), HaramConfig.class);
            }
        } catch (Throwable t) {
            HaramClient.LOGGER.warn("Failed to load mod config", t);
        }
    }

    // Save Config
    public static void save() {
        try {
            var path = FabricLoader.getInstance().getConfigDir().resolve("haramclient.json");
            Files.createDirectories(path.getParent());
            Files.writeString(path, GSON.toJson(new HaramConfig()));
        } catch (Throwable t) {
            HaramClient.LOGGER.warn("Failed to save mod config", t);
        }
    }
}