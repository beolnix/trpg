package com.beolnix.trpg.gameplay.scene.impl;

import com.beolnix.trpg.gameplay.scene.Scene;
import com.beolnix.trpg.gameplay.scene.impl.ExploreScreen;
import com.beolnix.trpg.model.Game;
import com.beolnix.trpg.model.Pers;
import com.beolnix.trpg.terminal.impl.InteractiveConsole;
import com.beolnix.trpg.terminal.model.InputOption;
import com.beolnix.trpg.terminal.model.UserInputRequest;
import com.beolnix.trpg.utils.ContentHelper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.*;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by beolnix on 30/08/15.
 */
public class ExploreScreenTestCase {

    private ByteArrayOutputStream os = new ByteArrayOutputStream();

    @Test
    public void generateUserInputRequestTest() {
        Set<Integer> exits = new HashSet<>();
        exits.add(3);
        exits.add(4);
        exits.add(2);

        ExploreScreen exploreScreen = new ExploreScreen(getConsole(), null);
        UserInputRequest userInputRequest = exploreScreen.generateUserInputRequest(exits);

        for (Integer providedExit : exits) {
            boolean foundFlag = false;
            for (InputOption option : userInputRequest.getOptions()) {
                if (option.getExpectedInput().equals(providedExit.toString())) {
                    foundFlag = true;
                }
            }
            assertTrue(foundFlag);
        }
    }

    @Test
    public void playTest() {
        Game game = new Game();
        game.setPers(new Pers());

        ExploreScreen screen = new ExploreScreen(getConsole(), game);
        Scene nextScene = screen.play();

        assertNotNull(nextScene);
        assertTrue(nextScene instanceof BattleScreen);
        assertEquals(getExpectedContent(), os.toString());
    }

    private String getExpectedContent() {
        return
                ContentHelper.getContent("/content/area_0_description.txt") + "\n" +
                ContentHelper.getContent("/content/area_0.txt") + "\n" +
                "What are you going to do?: \n" +
                        "     1 - go to exit 1\n" +
                        "     99 - lie and die\n" +
                        "Input: ";
    }

    private InteractiveConsole getConsole() {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));

        byte[] buf = "1\n".getBytes();
        InputStreamReader is = new InputStreamReader(new ByteArrayInputStream(buf));
        BufferedReader reader = new BufferedReader(is);

        return new InteractiveConsole(writer, reader);
    }


}
