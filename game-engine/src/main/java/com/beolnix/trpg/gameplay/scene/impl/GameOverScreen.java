package com.beolnix.trpg.gameplay.scene.impl;

import com.beolnix.trpg.model.Game;
import com.beolnix.trpg.utils.ContentHelper;
import com.beolnix.trpg.gameplay.scene.Scene;
import com.beolnix.trpg.gameplay.scene.SimpleTerminalScene;

/**
 * If Player failed, then he should see this screen.
 * Created by beolnix on 30/08/15.
 */
public class GameOverScreen extends SimpleTerminalScene {

    private final Game game;

    public GameOverScreen(Game game) {
        this.game = game;
    }

    @Override
    public Scene play() {

        println("It seems you've just died. Load saved game in file '" + game.getSavePath() + "' " +
                "using -g command line paramter and try again.\n\n\n\n");

        println(ContentHelper.getContent("/content/game_over.txt"));
        return null;
    }
}
