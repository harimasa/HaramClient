package ru.matt.haram;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
public class PigEat {
    public PigEat() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            PlayerEntity player = client.player;
            if (player != null && player.isUsingItem() && player.getActiveItem().getItem() == Items.COOKED_PORKCHOP) {
                player.sendMessage(Text.of("§cDont eat this is HARAM!!!"));
                for (int i = 0; i < player.getInventory().size(); i++) {
                    if (player.getInventory().getStack(i).getItem() != Items.COOKED_PORKCHOP) {
                        player.getInventory().selectedSlot = i;
                        break;
                    }
                }
                player.clearActiveItem();
            }
            if (player != null && player.isUsingItem() && player.getActiveItem().getItem() == Items.ROTTEN_FLESH) {
                player.sendMessage(Text.of("§cDont eat this is HARAM!!!"));
                for (int i = 0; i < player.getInventory().size(); i++) {
                    if (player.getInventory().getStack(i).getItem() != Items.ROTTEN_FLESH) {
                        player.getInventory().selectedSlot = i;
                        break;
                    }
                }
                player.clearActiveItem();
            }
            if (player != null && player.isUsingItem() && player.getActiveItem().getItem() == Items.PORKCHOP) {
                player.sendMessage(Text.of("§cDont eat this is HARAM!!!"));
                for (int i = 0; i < player.getInventory().size(); i++) {
                    if (player.getInventory().getStack(i).getItem() != Items.PORKCHOP) {
                        player.getInventory().selectedSlot = i;
                        break;
                    }
                }
                player.clearActiveItem();
            }
        });
    }
}