package com.beolnix.trpg.scene;

import com.beolnix.trpg.ContentHelper;
import com.beolnix.trpg.model.Game;

/**
 * Created by beolnix on 29/08/15.
 */
public class SplashScreen implements Scene {

    private static final String data = ContentHelper.getContent("/content/splashScreen.txt");
    private final Game game;

    public SplashScreen(Game game) {
        this.game = game;
    }

    public Scene play() {
        System.out.println(data);
        return new SelectCharacterScreen(game);
    }
}
