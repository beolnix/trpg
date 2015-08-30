package com.beolnix.trpg.gameplay.scene.impl;

import com.beolnix.trpg.gameplay.scene.Scene;
import com.beolnix.trpg.gameplay.scene.SimpleTerminalScene;
import com.beolnix.trpg.model.Game;
import com.beolnix.trpg.utils.ContentHelper;

import java.util.Random;

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
        if (isThereAnyAlien()) {
            boolean win = letsTheFightBegin();
            if (win) {
                return goNext();
            } else {
                return gameOver();
            }
        } else {
            return goNext();
        }
    }

    private Scene gameOver() {
        return new GameOverScreen();
    }

    private Scene goNext() {
        game.getPers().setPosition(nextPosition);
        return new ExploreScreen(game);
    }

    private boolean letsTheFightBegin() {
        println("You faced alien on your way.");
        println(ContentHelper.getContent("/content/alien.txt"));
        println("But he is intelligent. It doesn't want to fight with you. It asks you a puzzle instead.");
        return false;
    }

    private boolean isThereAnyAlien() {
        if (game.getWins() == 0) {
            return true;
        } else {
            Random random = new Random();
            int result = random.nextInt(4);
            return result == 1;
        }
    }
}
