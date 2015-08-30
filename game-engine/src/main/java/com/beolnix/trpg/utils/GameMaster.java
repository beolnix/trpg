package com.beolnix.trpg.utils;

import com.beolnix.trpg.error.Fatal;
import com.beolnix.trpg.model.Game;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.UUID;

/**
 * Created by beolnix on 30/08/15.
 */
public class GameMaster {

    public static Game loadOrCreateGame() {
        String name = UUID.randomUUID().toString();
        return loadOrCreateGame("./" + name + ".xml");
    }

    public static Game loadOrCreateGame(String path) {
        try {
            if (new File(path).exists()) {
                return GameMaster.loadGame(path);
            } else {
                Game game = new Game(path);
                GameMaster.saveGame(game, path);
                return game;
            }
        } catch (Fatal f) {
            System.out.println("ERROR: " + f.getMessage());
            System.exit(1);
            return null;
        }
    }

    public static Game loadGame(String path) throws Fatal {
        try {
            XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(path)));
            Game game = (Game) decoder.readObject();
            decoder.close();
            return game;
        } catch (IOException e) {
            throw new Fatal("Can't load saved game, file not found: " + path, e);
        }
    }

    public static void saveGame(Game game, String path) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            XMLEncoder encoder =
                    new XMLEncoder(
                            new BufferedOutputStream(
                                    new FileOutputStream(path)));
            encoder.writeObject(game);
            encoder.close();
        } catch (IOException e) {
            System.out.println("ERROR: can't save game because of " + e.getMessage());
            System.exit(1);
        }
    }



}
