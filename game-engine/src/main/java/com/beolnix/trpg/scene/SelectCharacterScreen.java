package com.beolnix.trpg.scene;

import com.beolnix.trpg.ContentHelper;
import com.beolnix.trpg.TerminalHelper;
import com.beolnix.trpg.model.Game;
import com.beolnix.trpg.model.InputOption;
import com.beolnix.trpg.model.Pers;
import com.beolnix.trpg.model.UserInputRequest;

/**
 * Created by beolnix on 30/08/15.
 */
public class SelectCharacterScreen implements Scene {

    private final Game game;
    private final UserInputRequest userInputRequest =
            new UserInputRequest("Enter",
                    new InputOption[]{new InputOption("1", "Skip"), new InputOption("2", "Accept")}
            );

    public SelectCharacterScreen(Game game) {
        this.game = game;
    }

    @Override
    public Scene play() {
        Pers pers = game.getPers();
        if (pers == null) {
            pers = selectCharacter();
            pers.setName(askName());
            game.setPers(pers);
        }
        return new ExploreScreen(game);
    }

    private String askName() {
        UserInputRequest userInputRequest = new UserInputRequest("Enter character name", new InputOption[]{});
        return TerminalHelper.askUserInput(userInputRequest);
    }

    private Pers selectCharacter() {
        Pers pers = null;
        int current = 1;
        System.out.println("Select character.");
        while (true) {
            printImage(current);
            String userInput = TerminalHelper.askUserInput(userInputRequest);

            if ("1".equals(userInput.trim())) {
                // display images in a cycle forever
                current = nextImage(current);
            } else if ("2".equals(userInput.trim())) {
                // stop if user choose some image
                pers = new Pers();
                pers.setType(current);
                return pers;
            }
        }
    }

    private int nextImage(int prevImage) {
        if (prevImage == 3) {
            return 1;
        } else {
            return prevImage + 1;
        }
    }

    private void printImage(int imageNumber) {
        String data = ContentHelper.getContent("/content/character_" + imageNumber + ".txt");
        System.out.println(data);
    }
}
