package com.beolnix.trpg.gameplay.scene.impl;

import com.beolnix.trpg.gameplay.scene.Scene;
import com.beolnix.trpg.gameplay.scene.SimpleTerminalScene;
import com.beolnix.trpg.model.Game;

/**
 * Created by beolnix on 30/08/15.
 */
public class BattleScreen extends SimpleTerminalScene {

    private final Integer nextPosition;
    private final Game game;

    public BattleScreen(Game game, Integer nextPosition) {
        this.nextPosition = nextPosition;
        this.game = game;
    }

    @Override
    public Scene play() {
        game.getPers().setPosition(nextPosition);
        return new ExploreScreen(game);
    }
}
