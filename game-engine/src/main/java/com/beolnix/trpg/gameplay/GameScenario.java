package com.beolnix.trpg.gameplay;

import com.beolnix.trpg.terminal.SimpleTerminal;
import com.beolnix.trpg.utils.GameMaster;
import com.beolnix.trpg.gameplay.scene.*;
import com.beolnix.trpg.gameplay.scene.impl.*;
import com.beolnix.trpg.model.Game;

/**
 * Created by beolnix on 30/08/15.
 */
public class GameScenario {

    public static void run(Game game) {
        Scene splashScreen = new SplashScreen(game);
        splashScreen.play();

        delay(2, splashScreen.getTerminal());

        Scene selectScene = new SelectCharacterScreen(game);
        selectScene.play();

        delay(1, selectScene.getTerminal());

        GameMaster.saveGame(game, game.getSavePath());

        Scene nextScene = new ExploreScreen(game);

        // indefinitely walking between scenes until next one is gameover or finish game scene
        // saving the game between scenes
        do {
            nextScene = nextScene.play();
            if (!isItBattle(nextScene)) {
                GameMaster.saveGame(game, game.getSavePath());
            }
            delay(1, nextScene.getTerminal());
        } while (!isItTheEnd(nextScene));

        nextScene.play();

    }

    private static void delay(int secs, SimpleTerminal terminal) {
        try {
            long delay = secs * 1000;
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

    private static boolean isItBattle(Scene scene) {
        return scene instanceof BattleScreen;
    }

    private static boolean isItTheEnd(Scene scene) {
        return (scene instanceof GameOverScreen) ||
                (scene instanceof FinishGameScreen);
    }
}
