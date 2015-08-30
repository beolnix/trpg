package com.beolnix.trpg.gameplay.quiz.model;

/**
 * Created by beolnix on 30/08/15.
 */
public class AnswerOption {
    int number;
    String text;

    public AnswerOption(int number, String text) {
        this.number = number;
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnswerOption that = (AnswerOption) o;

        if (number != that.number) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = number;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }

    public int getNumber() {
        return number;
    }

    public String getText() {
        return text;
    }
}
