package ru.matt.api.discord;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.minecraft.SharedConstants;
import net.minecraft.client.MinecraftClient;

import java.util.Timer;
import java.util.TimerTask;

public class DiscordSS {
    public static final MinecraftClient mc = MinecraftClient.getInstance();
    public static final DiscordRPC haram = DiscordRPC.INSTANCE;
    public static final DiscordEventHandlers handlers = new DiscordEventHandlers();
    public static Long stg;
    private static final Timer TIMER = new Timer();
    private static String exp;

    public static void init() {
        stg = System.currentTimeMillis() / 1000;
        ClientLifecycleEvents.CLIENT_STOPPING.register(client -> {
            haram.Discord_Shutdown();
        });
        haram.Discord_Initialize("1234243048997912728", handlers, true, null);
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                haram.Discord_RunCallbacks();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ignored) {
                }
            }
        }, "RPC").start();
        start();
    }

    private static void start(){
        TIMER.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    updatePresence();
                    if(exp != null) exp = null;
                } catch(Exception e){
                    if(exp == null || !exp.equals(e.getMessage())){
                        e.printStackTrace();
                        DiscordRichPresence presence = new DiscordRichPresence();
                        presence.details = "Error";
                        haram.Discord_UpdatePresence(presence);
                        exp = e.getMessage();
                    }
                }
            }
        }, 2500, 2500);
    }

    private static void updatePresence() {
        if (mc.world == null || mc.player == null) {
            if (States.getGameState() == 1) {
                DiscordRichPresence presence = new DiscordRichPresence();
                presence.details = "Playing Minecraft " + SharedConstants.getGameVersion().getName();
                presence.state = "Loading game";
                DiscordSS.updateDiscordPresence(presence);
            } else if (States.getGameState() == 2) {
                DiscordRichPresence presence = new DiscordRichPresence();
                presence.details = "Playing Minecraft " + SharedConstants.getGameVersion().getName();
                presence.state = "Connecting to a server";
                DiscordSS.updateDiscordPresence(presence);
            } else if (States.getGameState() == 3) {
                DiscordRichPresence presence = new DiscordRichPresence();
                presence.details = "Playing Minecraft " + SharedConstants.getGameVersion().getName();
                presence.state = "Disconnected from a server";
                DiscordSS.updateDiscordPresence(presence);
            } else {
                DiscordRichPresence presence = new DiscordRichPresence();
                presence.details = "Playing Minecraft " + SharedConstants.getGameVersion().getName();
                presence.state = "In the main menu";
                DiscordSS.updateDiscordPresence(presence);
            }
        } else {
            if (mc.isInSingleplayer()) {
                DiscordRichPresence presence = new DiscordRichPresence();
                presence.details = "Playing Minecraft " + SharedConstants.getGameVersion().getName();
                presence.state = "Singleplayer";
                DiscordSS.updateDiscordPresence(presence);
            } else if (mc.getCurrentServerEntry() != null) {
                DiscordRichPresence presence = new DiscordRichPresence();
                presence.details = "Playing Minecraft " + SharedConstants.getGameVersion().getName();
                presence.state = mc.getCurrentServerEntry().address;
                DiscordSS.updateDiscordPresence(presence);
            } else {
                DiscordRichPresence presence = new DiscordRichPresence();
                presence.details = "Playing Minecraft " + SharedConstants.getGameVersion().getName();
                presence.state = "In the main menu";
                DiscordSS.updateDiscordPresence(presence);
            }
        }
    }

    public static void updateDiscordPresence(DiscordRichPresence presence){
        presence.largeImageKey = "haram";
        presence.largeImageText = "Haram Client";
        presence.startTimestamp = stg;
        haram.Discord_UpdatePresence(presence);
    }
}