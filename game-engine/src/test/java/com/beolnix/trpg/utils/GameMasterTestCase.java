package com.beolnix.trpg.utils;

import com.beolnix.trpg.utils.GameMaster;
import com.beolnix.trpg.error.Fatal;
import com.beolnix.trpg.model.Game;
import com.beolnix.trpg.model.Pers;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by beolnix on 30/08/15.
 */
public class GameMasterTestCase {

    @Test
    public void saveAndLoadTest() throws Fatal {
        Game game = createTestGame();

        GameMaster.saveGame(game, game.getSavePath());

        Game newGame = GameMaster.loadGame(game.getSavePath());

        assertEquals(game, newGame);
    }

    @Test
    public void loadOrCreateTest() throws Fatal {
        String newPath = "./test_new_path.xml";
        Game game = GameMaster.loadOrCreateGame(newPath);
        assertNotNull(game);
    }

    @Test
    public void loadOrCreateTest2() throws Fatal {
        Game game = GameMaster.loadOrCreateGame();
        assertNotNull(game);
    }

    @Test(expected = Fatal.class)
    public void loadFailure() throws Fatal {
        Game game = GameMaster.loadGame("strange_path");
    }


    public Game createTestGame() {
        Pers pers = new Pers();
        pers.setName("test name");

        Game game = new Game();
        game.setPers(pers);
        game.setSavePath("./test.xml");

        return game;
    }
}
