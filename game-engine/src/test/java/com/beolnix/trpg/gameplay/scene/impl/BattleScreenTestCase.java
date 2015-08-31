package com.beolnix.trpg.gameplay.scene.impl;

import com.beolnix.trpg.gameplay.scene.Scene;
import com.beolnix.trpg.model.Game;
import com.beolnix.trpg.model.Pers;
import com.beolnix.trpg.terminal.impl.InteractiveConsole;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.*;

/**
 * Created by beolnix on 30/08/15.
 */
public class BattleScreenTestCase {

    private ByteArrayOutputStream os = new ByteArrayOutputStream();

    @Test
    public void winBattleTest() {
        Game game = new Game();
        game.setPers(new Pers());

        BattleScreen screen = new BattleScreen(getConsole("1"), game, 1);
        Scene nextScene = screen.play();

        assertNotNull(nextScene);
        assertTrue(nextScene instanceof ExploreScreen);
    }

    @Test
    public void looseBattleTest() {
        Game game = new Game();
        game.setPers(new Pers());

        BattleScreen screen = new BattleScreen(getConsole("0"), game, 1);
        Scene nextScene = screen.play();

        assertNotNull(nextScene);
        assertTrue(nextScene instanceof GameOverScreen);
    }

    private InteractiveConsole getConsole(String input) {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));

        byte[] buf = (input + "\n").getBytes();
        InputStreamReader is = new InputStreamReader(new ByteArrayInputStream(buf));
        BufferedReader reader = new BufferedReader(is);

        return new InteractiveConsole(writer, reader);
    }
}
