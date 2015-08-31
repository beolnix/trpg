package com.beolnix.trpg.gameplay;

import com.beolnix.trpg.gameplay.scene.Scene;
import com.beolnix.trpg.gameplay.scene.impl.*;
import com.beolnix.trpg.model.Game;
import com.beolnix.trpg.terminal.SimpleTerminal;
import com.beolnix.trpg.utils.GameMaster;

/**
 * Class describes how the game goes. Step by Step it executes Scene.play() methods
 * Sequence of Scene.play() is the game it self.
 * Created by beolnix on 30/08/15.
 */
public class GameScenario {

    private final SimpleTerminal terminal;

    public GameScenario(SimpleTerminal terminal) {
        this.terminal = terminal;
    }

    public void run(Game game) {
        new SplashScreen(terminal, game).play();
        delay(2);

        new SelectCharacterScreen(terminal, game).play();
        delay(1);

        GameMaster.saveGame(game, game.getSavePath());

        Scene nextScene = new ExploreScreen(terminal, game).play();

        // indefinitely walking between scenes until next one is gameover or finish game scene
        // saving the game between scenes
        do {
            nextScene = nextScene.play();
            GameMaster.saveGame(game, game.getSavePath());
            delay(1);
        } while (!isItTheEnd(nextScene));

        nextScene.play();
    }

    /**
     * makes delay for provided number of secs and
     * prints dots on the screen during the delay
     *
     * @param secs
     */
    private void delay(int secs) {
        long delay = secs * 1000L;
        long start = System.currentTimeMillis();
        long current = System.currentTimeMillis();

        while ((current - start) < delay) {
            unsafeDelay(50);
            terminal.print(".");
            current = System.currentTimeMillis();
        }

        terminal.println("");
    }

    private void unsafeDelay(int milisecs) {
        try {
            Thread.sleep(milisecs);
        } catch (InterruptedException e) {
            //nop
        }
    }

    private boolean isItTheEnd(Scene scene) {
        return (scene instanceof GameOverScreen) ||
                (scene instanceof FinishGameScreen);
    }
}
