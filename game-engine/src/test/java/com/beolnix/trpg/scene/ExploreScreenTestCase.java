package com.beolnix.trpg.scene;

import com.beolnix.trpg.model.InputOption;
import com.beolnix.trpg.model.UserInputRequest;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by beolnix on 30/08/15.
 */
public class ExploreScreenTestCase {

    @Test
    public void generateUserInputRequestTest() {
        Set<Integer> exits = new HashSet<>();
        exits.add(3);
        exits.add(4);
        exits.add(2);

        ExploreScreen exploreScreen = new ExploreScreen(null);
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

    private void changeModifier() {
        Class cls = ExploreScreen.class;
        Method method = cls.getMethods()[1];



        method.setAccessible(true);
    }
}
