package com.beolnix.trpg.gameplay.scene.impl;

import com.beolnix.trpg.utils.ContentHelper;
import com.beolnix.trpg.gameplay.scene.Scene;
import com.beolnix.trpg.gameplay.scene.SimpleTerminalScene;
import com.beolnix.trpg.model.Game;
import com.beolnix.trpg.terminal.model.InputOption;
import com.beolnix.trpg.model.Pers;
import com.beolnix.trpg.terminal.model.UserInputRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by beolnix on 30/08/15.
 */
public class SelectCharacterScreen extends SimpleTerminalScene {

    private final Game game;

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
        } else {
            displayWelcome();
        }
        return new ExploreScreen(game);
    }

    private void displayWelcome() {
        System.out.println("Welcome, " + game.getPers().getName() + " back!");
        printImage(game.getPers().getImage());
    }

    private String askName() {
        return askUserInput(getNameUserInputRequest()).getActualInput();
    }

    private Pers selectCharacter() {
        int currentImage = 1;
        System.out.println("Select character.");
        while (true) {
            printImage(currentImage);
            InputOption inputOption = askUserInput(getImageUserInputRequest());

            if ("1".equals(inputOption.getExpectedInput())) {
                // display images in a cycle forever
                currentImage = nextImage(currentImage);
            } else if ("2".equals(inputOption.getExpectedInput())) {
                // stop if user choose some image
                Pers pers = new Pers();
                pers.setImage(currentImage);
                return pers;
            }
        }
    }

    private int nextImage(int prevImage) {
        if (prevImage >= 3) {
            return 1;
        } else {
            return prevImage + 1;
        }
    }

    private void printImage(int imageNumber) {
        String data = ContentHelper.getContent("/content/character_" + imageNumber + ".txt");
        System.out.println(data);
    }

    private UserInputRequest getNameUserInputRequest() {
        return new UserInputRequest("Enter character name", Collections.emptyList());
    }

    private UserInputRequest getImageUserInputRequest() {
        List<InputOption> inputOptionList = new ArrayList<>();
        inputOptionList.add(new InputOption("1", "Skip"));
        inputOptionList.add(new InputOption("2", "Accept"));

        return new UserInputRequest("Enter",
                inputOptionList
        );
    }
}
