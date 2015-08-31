package com.beolnix.trpg.gameplay.scene.impl;

import com.beolnix.trpg.gameplay.scene.Scene;
import com.beolnix.trpg.terminal.impl.InteractiveConsole;
import com.beolnix.trpg.utils.ContentHelper;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

/**
 * Created by beolnix on 30/08/15.
 */
public class SplashScreenTestCase {

    ByteArrayOutputStream os = new ByteArrayOutputStream();

    @Test
    public void playTest() {
        SplashScreen splashScreen = new SplashScreen(getConsole(), null);
        Scene scene = splashScreen.play();

        assertNotNull(scene);
        assertTrue(scene instanceof SelectCharacterScreen);
        assertEquals(ContentHelper.getContent("/content/splash_screen.txt") + "\n", os.toString());
    }

    private InteractiveConsole getConsole() {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));

        byte[] buf = "".getBytes();
        InputStreamReader is = new InputStreamReader(new ByteArrayInputStream(buf));
        BufferedReader reader = new BufferedReader(is);

        return new InteractiveConsole(writer, reader);
    }
}
