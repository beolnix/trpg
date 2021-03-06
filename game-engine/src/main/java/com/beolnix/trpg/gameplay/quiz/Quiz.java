package com.beolnix.trpg.gameplay.quiz;

import com.beolnix.trpg.gameplay.quiz.model.Question;
import com.beolnix.trpg.utils.ContentHelper;

import java.util.ArrayList;
import java.util.Random;

/**
 * Quiz service based on /content/questions.txt.
 * Main function of it is to provide random question.
 * Created by beolnix on 30/08/15.
 */
public class Quiz {

    private final ArrayList<Question> questions;
    private static Quiz instance;

    static {
        String content = ContentHelper.getContent("/content/questions.txt");
        ArrayList<Question> questions = QuizParser.parseContent(content);
        instance = new Quiz(questions);
    }

    private Quiz(ArrayList<Question> questions) {
        this.questions = questions;
    }

    /**
     * Returns instance of the Quiz
     * @return
     */
    public static Quiz getInstance() {
        return instance;
    }

    /**
     * Returns randomly selected question.
     * @return
     */
    public static Question getRandomQuestion() {
        Random random = new Random();
        int questionNumber = random.nextInt(instance.questions.size());
        return instance.questions.get(questionNumber);
    }


}
