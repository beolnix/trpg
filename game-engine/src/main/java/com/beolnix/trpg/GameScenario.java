package com.beolnix.trpg;

import com.beolnix.trpg.model.Game;
import com.beolnix.trpg.scene.ExploreScreen;
import com.beolnix.trpg.scene.SelectCharacterScreen;
import com.beolnix.trpg.scene.SplashScreen;

/**
 * Created by beolnix on 30/08/15.
 */
public class GameScenario {

    public static void run(Game game) {
        new SplashScreen(game).play();

        new SelectCharacterScreen(game).play();

        GameMaster.saveGame(game, game.getSavePath());

        new ExploreScreen(game).play();

    }
}
