package com.beolnix.trpg.gameplay.quiz;

import com.beolnix.trpg.error.QuizFatal;
import com.beolnix.trpg.gameplay.quiz.model.AnswerOption;
import com.beolnix.trpg.gameplay.quiz.model.Question;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Service used to parse /content/questions.txt and parse the fail
 * into the collection of question objects.
 * Created by beolnix on 30/08/15.
 */
public class QuizParser {

    private QuizParser() {}

    public static ArrayList<Question> parseContent(String content) {
        String[] rawQuestions = content.split("=========================================================\n");
        ArrayList<Question> questions = new ArrayList<>();

        for (String rawQuestion : rawQuestions) {
            questions.add(parseQuestion(rawQuestion));
        }

        return questions;
    }

    private static Question parseQuestion(String rawQuestion) {
        String[] questionParts = rawQuestion.split("\n");
        String questionString = questionParts[0];
        Set<AnswerOption> answerOptions = parseAnswerOptions(questionParts[1].split(";"));
        AnswerOption correctAnswer = determineCorrectAnswer(answerOptions, questionParts[2]);

        return new Question(questionString, answerOptions, correctAnswer);
    }

    private static Set<AnswerOption> parseAnswerOptions(String[] answerOptionsRaw) {
        Set<AnswerOption> answerOptions = new HashSet<>();
        for (int i = 0; i < answerOptionsRaw.length; i++) {
            answerOptions.add(new AnswerOption(i, answerOptionsRaw[i].trim()));
        }

        return answerOptions;
    }

    private static AnswerOption determineCorrectAnswer(Set<AnswerOption> answerOptions, String rawAnswer) {
        for (AnswerOption option : answerOptions) {
            if (option.getText().trim().equals(rawAnswer.trim())) {
                return option;
            }
        }

        throw new QuizFatal("can't determine correct answer for: " + rawAnswer);
    }
}
