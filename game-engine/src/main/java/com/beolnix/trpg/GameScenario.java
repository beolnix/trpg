package com.beolnix.trpg;

import com.beolnix.trpg.model.Game;
import com.beolnix.trpg.scene.*;

/**
 * Created by beolnix on 30/08/15.
 */
public class GameScenario {

    public static void run(Game game) {
        new SplashScreen(game).play();

        new SelectCharacterScreen(game).play();

        GameMaster.saveGame(game, game.getSavePath());

        Scene nextScene = new ExploreScreen(game);
        do {
            nextScene = nextScene.play();
            if (!(nextScene instanceof BattleScreen)) {
                GameMaster.saveGame(game, game.getSavePath());
            }
        } while (!(nextScene instanceof GameOverScreen));

        nextScene.play();

    }
}
