package com.beolnix.trpg.scene;

import com.beolnix.trpg.model.Game;

/**
 * Created by beolnix on 30/08/15.
 */
public class BattleScreen implements Scene {

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
