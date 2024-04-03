package ru.matt.JLayers;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.IOException;
import java.io.InputStream;

public class JLayerInit {
    public void play(String filePath) {
        try {
            InputStream fis = getClass().getResourceAsStream(filePath);
            if (fis == null) {
                throw new IOException("File not found: " + filePath);
            }
            Player player = new Player(fis);
            player.play();
        } catch (JavaLayerException | IOException e) {
            e.printStackTrace();
        }
    }
}