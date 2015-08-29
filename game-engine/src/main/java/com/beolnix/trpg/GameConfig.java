package com.beolnix.trpg;

/**
 * Created by beolnix on 29/08/15.
 */
public class GameConfig {
    private boolean displayHelp = false;
    private String saveFilePath;
    private String gameName;

    public String getSaveFilePath() {
        return saveFilePath;
    }

    public void setSaveFilePath(String saveFilePath) {
        this.saveFilePath = saveFilePath;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public boolean isDisplayHelp() {
        return displayHelp;
    }

    public void setDisplayHelp(boolean displayHelp) {
        this.displayHelp = displayHelp;
    }
}
