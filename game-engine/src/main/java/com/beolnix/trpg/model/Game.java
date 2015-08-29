package com.beolnix.trpg.model;

import java.io.Serializable;

/**
 * Created by beolnix on 29/08/15.
 */
public class Game implements Serializable {

    private static final long serialVersionUID = -6600710799904808819L;
    private String savePath;
    private Pers pers;

    // for deserealization
    public Game() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        if (pers != null ? !pers.equals(game.pers) : game.pers != null) return false;
        if (savePath != null ? !savePath.equals(game.savePath) : game.savePath != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = savePath != null ? savePath.hashCode() : 0;
        result = 31 * result + (pers != null ? pers.hashCode() : 0);
        return result;
    }

    public Game(String savePath) {
        this.savePath = savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public String getSavePath() {
        return savePath;
    }

    public Pers getPers() {
        return pers;
    }

    public void setPers(Pers pers) {
        this.pers = pers;
    }
}
