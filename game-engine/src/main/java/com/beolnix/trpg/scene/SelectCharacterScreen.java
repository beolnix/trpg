package com.beolnix.trpg.scene;

import com.beolnix.trpg.model.Game;

/**
 * Created by beolnix on 30/08/15.
 */
public class SelectCharacterScreen implements Scene {

    private final Game game;

    public SelectCharacterScreen(Game game) {
        this.game = game;
    }

    @Override
    public Scene play() {
        return null;
    }
}
