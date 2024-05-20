package ru.matt.haram;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import ru.matt.api.modmenu.HaramConfig;

public class Kill {
    private long lastMessageTime = 0;
    private static final long milles_delay = 100;

    public Kill() {
        AttackEntityCallback.EVENT.register((player, world, hand, entity, entityHitResult) -> {
            long currentTime = System.currentTimeMillis();
            if (HaramConfig.enabledKill) {
                if (currentTime - lastMessageTime > milles_delay) {
                    MinecraftClient.getInstance().execute(() -> {
                        player.sendMessage(Text.of("§cYou can't fight. It's a violation of the Koran. Haram!!!"));
                    });
                    lastMessageTime = currentTime;
                }
            }
            return ActionResult.PASS;
        });
    }
}