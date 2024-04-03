package ru.matt.haram;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import ru.matt.JLayers.JLayerInit;

public class Koran {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        LiteralArgumentBuilder<ServerCommandSource> command = CommandManager.literal("koran")
                .executes(context -> {
                    JLayerInit player = new JLayerInit();
                    player.play("/assets/haramclient/Sound/koran.mp3");
                    return 1;
                });
        dispatcher.register(command);
    }
}