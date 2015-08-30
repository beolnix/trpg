package com.beolnix.trpg.gameplay;

import com.beolnix.trpg.utils.GameMaster;
import com.beolnix.trpg.gameplay.scene.*;
import com.beolnix.trpg.gameplay.scene.impl.*;
import com.beolnix.trpg.model.Game;

/**
 * Created by beolnix on 30/08/15.
 */
public class GameScenario {

    public static void run(Game game) {
        new SplashScreen(game).play();

        new SelectCharacterScreen(game).play();

        GameMaster.saveGame(game, game.getSavePath());

        Scene nextScene = new ExploreScreen(game);

        // indefinitely walking between scenes until next one is gameover or finish game scene
        // saving the game between scenes
        do {
            nextScene = nextScene.play();
            if (!isItBattle(nextScene)) {
                GameMaster.saveGame(game, game.getSavePath());
            }
        } while (!isItTheEnd(nextScene));

        nextScene.play();

    }

    private static boolean isItBattle(Scene scene) {
        return scene instanceof BattleScreen;
    }

    private static boolean isItTheEnd(Scene scene) {
        return (scene instanceof GameOverScreen) ||
                (scene instanceof FinishGameScreen);
    }
}
