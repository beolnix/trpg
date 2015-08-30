package com.beolnix.trpg.gameplay.quiz;

import com.beolnix.trpg.gameplay.quiz.model.AnswerOption;
import com.beolnix.trpg.gameplay.quiz.model.Question;
import com.beolnix.trpg.utils.ContentHelper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by beolnix on 30/08/15.
 */
public class QuizParserTestCase {

    @Test
    public void parseQuizTest() {
        ArrayList<Question> questions = QuizParser.parseContent(ContentHelper.getContent("/content/test_quiz.txt"));

        assertEquals(2, questions.size());
        assertTrue(questions.contains(getFirstQuestoin()));
    }

    public Question getFirstQuestoin() {
        AnswerOption one = new AnswerOption(0, "Yes");
        AnswerOption two = new AnswerOption(1, "No");

        Set<AnswerOption> answerOptions = new HashSet<>();
        answerOptions.add(two);
        answerOptions.add(one);

        return new Question("Do goldfish really have a memory of three seconds?", answerOptions, two);
    }
}
