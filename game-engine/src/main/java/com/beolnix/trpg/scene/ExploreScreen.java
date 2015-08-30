package com.beolnix.trpg.scene;

import com.beolnix.trpg.Area;
import com.beolnix.trpg.TerminalHelper;
import com.beolnix.trpg.model.Game;
import com.beolnix.trpg.model.InputOption;
import com.beolnix.trpg.model.UserInputRequest;

import java.util.*;

/**
 * Created by beolnix on 30/08/15.
 */
public class ExploreScreen implements Scene {

    private final Game game;

    public ExploreScreen(Game game) {
        this.game = game;
    }

    @Override
    public Scene play() {
        int position = game.getPers().getPosition();
        Area area = Area.loadAreaForPosition(position);
        System.out.println(area.getDescription());
        System.out.println(area.getAsciiMap());
        String userInput = TerminalHelper.askUserInput(generateUserInputRequest(area.getExits()));

        Integer userInputNumber = Integer.parseInt(userInput);

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
