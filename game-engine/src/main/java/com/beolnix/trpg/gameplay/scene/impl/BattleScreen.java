package com.beolnix.trpg.gameplay.scene.impl;

import com.beolnix.trpg.gameplay.quiz.Quiz;
import com.beolnix.trpg.gameplay.quiz.model.AnswerOption;
import com.beolnix.trpg.gameplay.quiz.model.Question;
import com.beolnix.trpg.gameplay.scene.Scene;
import com.beolnix.trpg.gameplay.scene.SimpleTerminalScene;
import com.beolnix.trpg.model.Game;
import com.beolnix.trpg.terminal.model.InputOption;
import com.beolnix.trpg.terminal.model.UserInputRequest;
import com.beolnix.trpg.utils.ContentHelper;

import java.util.ArrayList;
import java.util.Random;

/**
 * Battle scene is the scene when Alien ask you a question and provide several answer options.
 * Player may die on this scene if he provides incorrect answer or go to the next scene;
 * Created by beolnix on 30/08/15.
 */
public class BattleScreen extends SimpleTerminalScene {

    private final Integer nextPosition;
    private final Game game;

    public BattleScreen(Game game, Integer nextPosition) {
        this.nextPosition = nextPosition;
        this.game = game;
    }

    @Override
    public Scene play() {
        if (isThereAnyAlien()) {
            boolean win = letsTheFightBegin();
            if (win) {
                println("Your answer is correct. Alien let you go and you pass through.");
                game.setWins(game.getWins() + 1);
                return goNext();
            } else {
                println("Your answer is incorrect. Alien becames crazy and kill you.");
                return gameOver();
            }
        } else {
            return goNext();
        }
    }

    private Scene gameOver() {
        return new GameOverScreen(game);
    }

    private Scene goNext() {
        game.getPers().setPosition(nextPosition);
        return new ExploreScreen(game);
    }

    private boolean letsTheFightBegin() {
        println(ContentHelper.getContent("/content/alien.txt"));

        Question question = Quiz.getRandomQuestion();
        UserInputRequest inputRequest = createInputRequest(question);
        InputOption inputOption = askUserInput(inputRequest);

        return isAnswerCorrect(question, inputOption);
    }

    private boolean isAnswerCorrect(Question question, InputOption inputOption) {
        return question.getCorrectAnswer().getNumber() == Integer.parseInt(inputOption.getActualInput());
    }

    private UserInputRequest createInputRequest(Question question) {
        ArrayList<InputOption> inputOptions = new ArrayList<>();
        for (AnswerOption answerOption : question.getOptions()) {
            InputOption inputOption = new InputOption(answerOption.getNumber() + "", answerOption.getText());
            inputOptions.add(inputOption);
        }

        return new UserInputRequest(question.getQuestion(), inputOptions);
    }


    private boolean isThereAnyAlien() {
        if (game.getWins() == 0) {
            return true;
        } else {
            Random random = new Random();
            int result = random.nextInt(2);
            return result == 1;
        }
    }
}
