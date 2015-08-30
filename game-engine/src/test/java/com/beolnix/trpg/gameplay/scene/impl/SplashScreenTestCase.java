package com.beolnix.trpg.gameplay.scene.impl;

import com.beolnix.trpg.gameplay.scene.Scene;
import com.beolnix.trpg.gameplay.scene.SimpleTerminalScene;
import com.beolnix.trpg.terminal.SimpleTerminal;
import com.beolnix.trpg.terminal.impl.InteractiveConsole;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

/**
 * Created by beolnix on 30/08/15.
 */
public class SplashScreenTestCase {

    @Test
    public void playTest() {
        SplashScreen splashScreen = new SplashScreen(null);
        splashScreen.setTerminal(getConsole());
        Scene scene = splashScreen.play();

        assertNotNull(scene);
        assertTrue(scene instanceof SelectCharacterScreen);
    }

    private InteractiveConsole getConsole() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));

        byte[] buf = "".getBytes();
        InputStreamReader is = new InputStreamReader(new ByteArrayInputStream(buf));
        BufferedReader reader = new BufferedReader(is);

        return new InteractiveConsole(writer, reader);
    }
}
