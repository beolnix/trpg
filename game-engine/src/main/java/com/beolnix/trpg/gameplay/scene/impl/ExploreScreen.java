package com.beolnix.trpg.gameplay.scene.impl;

import com.beolnix.trpg.gameplay.scene.Scene;
import com.beolnix.trpg.gameplay.scene.SimpleTerminalScene;
import com.beolnix.trpg.model.Game;
import com.beolnix.trpg.terminal.model.InputOption;
import com.beolnix.trpg.terminal.model.UserInputRequest;

import java.util.*;

/**
 * Created by beolnix on 30/08/15.
 */
public class ExploreScreen extends SimpleTerminalScene {

    private final Game game;

    public ExploreScreen(Game game) {
        this.game = game;
    }

    @Override
    public Scene play() {
        int position = game.getPers().getPosition();
        Area area = Area.loadAreaForPosition(position);
        println(area.getDescription());
        println(area.getAsciiMap());
        InputOption userInput = askUserInput(generateUserInputRequest(area.getExits()));

        Integer userInputNumber = Integer.parseInt(userInput.getExpectedInput());

        if (area.getExits().contains(userInputNumber)) {
            return new BattleScreen(game, userInputNumber);
        }

        return new GameOverScreen();
    }

    UserInputRequest generateUserInputRequest(Set<Integer> exits) {

        List<InputOption> exitsOptions = new ArrayList<>();
        exits.stream().forEach( exit ->
            exitsOptions.add(new InputOption(exit.toString(), "go to exit " + exit))
        );

        exitsOptions.add(new InputOption(exitsOptions.size() + 1 + "", "lie and die"));

        UserInputRequest userInputRequest = new UserInputRequest(
                "What are you going to do?",
                exitsOptions
        );

        return userInputRequest;
    }



}
