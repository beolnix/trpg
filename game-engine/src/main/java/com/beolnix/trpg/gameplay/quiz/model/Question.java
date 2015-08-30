package com.beolnix.trpg.gameplay.quiz.model;

import java.util.Set;

/**
 * Model used to save the information about question including
 * the question itself, answer options and the correct answer.
 * Created by beolnix on 30/08/15.
 */
public class Question {
    private final String question;
    private final Set<AnswerOption> options;
    private final AnswerOption correctAnswer;

    public Question(String question, Set<AnswerOption> options, AnswerOption correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question1 = (Question) o;

        if (correctAnswer != null ? !correctAnswer.equals(question1.correctAnswer) : question1.correctAnswer != null)
            return false;
        if (options != null ? !options.equals(question1.options) : question1.options != null) return false;
        if (question != null ? !question.equals(question1.question) : question1.question != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = question != null ? question.hashCode() : 0;
        result = 31 * result + (options != null ? options.hashCode() : 0);
        result = 31 * result + (correctAnswer != null ? correctAnswer.hashCode() : 0);
        return result;
    }

    public String getQuestion() {
        return question;
    }

    public Set<AnswerOption> getOptions() {
        return options;
    }

    public AnswerOption getCorrectAnswer() {
        return correctAnswer;
    }
}
