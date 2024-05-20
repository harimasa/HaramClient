package ru.matt.haram;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import ru.matt.JLayers.JLayerInit;

public class Koran {
    public static void register() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(ClientCommandManager.literal("koran")
                    .executes(context -> {
                        JLayerInit player = new JLayerInit();
                        player.play("/assets/haramclient/Sound/koran.mp3");
                        return 1;
                    }));
        });
    }
}