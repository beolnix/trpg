package com.beolnix.trpg.gameplay.scene.impl;

import com.beolnix.trpg.gameplay.scene.Scene;
import com.beolnix.trpg.model.Game;
import com.beolnix.trpg.terminal.impl.InteractiveConsole;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.*;

/**
 * Created by beolnix on 30/08/15.
 */
public class SelectCharacterScreenTestCase {

    private int expectedImage = 2;
    private String expectedName = "TestName";

    @Test
    public void playTest() {
        Game game = new Game();
        SelectCharacterScreen selectCharacterScreen = new SelectCharacterScreen(game);
        selectCharacterScreen.setTerminal(getConsole());
        Scene scene = selectCharacterScreen.play();

        assertEquals(expectedImage, game.getPers().getImage());
        assertEquals(expectedName, game.getPers().getName());
        assertEquals(0, game.getPers().getPosition());

        assertNotNull(scene);
    }

    private InteractiveConsole getConsole() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));

        byte[] buf = ("1\n" + expectedImage + "\n" + expectedName +"\n").getBytes();
        InputStreamReader is = new InputStreamReader(new ByteArrayInputStream(buf));
        BufferedReader reader = new BufferedReader(is);

        return new InteractiveConsole(writer, reader);
    }

}
