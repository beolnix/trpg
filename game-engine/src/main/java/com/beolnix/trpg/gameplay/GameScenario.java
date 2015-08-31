package com.beolnix.trpg.gameplay;

import com.beolnix.trpg.terminal.SimpleTerminal;
import com.beolnix.trpg.utils.GameMaster;
import com.beolnix.trpg.gameplay.scene.*;
import com.beolnix.trpg.gameplay.scene.impl.*;
import com.beolnix.trpg.model.Game;

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


    private void delay(int secs) {
        try {
            long delay = secs * 1000L;
            long start = System.currentTimeMillis();

            while (true) {
                Thread.sleep(50);
                terminal.print(".");
                long current = System.currentTimeMillis();
                if ((current - start) > delay) {
                    terminal.println("");
                    return;
                }
            }
        } catch (InterruptedException e) {
            //nop
        }
    }

    private static boolean isItTheEnd(Scene scene) {
        return (scene instanceof GameOverScreen) ||
                (scene instanceof FinishGameScreen);
    }
}
