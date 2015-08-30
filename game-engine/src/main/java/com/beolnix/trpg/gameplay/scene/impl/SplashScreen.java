package com.beolnix.trpg.gameplay.scene.impl;

import com.beolnix.trpg.utils.ContentHelper;
import com.beolnix.trpg.gameplay.scene.Scene;
import com.beolnix.trpg.gameplay.scene.SimpleTerminalScene;
import com.beolnix.trpg.model.Game;

/**
 * The first screen, demonstrates splash screen image.
 * Created by beolnix on 29/08/15.
 */
public class SplashScreen extends SimpleTerminalScene {

    private static final String data = ContentHelper.getContent("/content/splash_screen.txt");
    private final Game game;

    public SplashScreen(Game game) {
        this.game = game;
    }

    @Override
    public Scene play() {
        println(data);
        return new SelectCharacterScreen(game);
    }
}
