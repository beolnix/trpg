package com.beolnix.trpg.gameplay.scene.impl;

import com.beolnix.trpg.gameplay.scene.Scene;
import com.beolnix.trpg.gameplay.scene.SimpleTerminalScene;
import com.beolnix.trpg.model.Game;
import com.beolnix.trpg.terminal.SimpleTerminal;
import com.beolnix.trpg.utils.ContentHelper;

/**
 * Created by beolnix on 30/08/15.
 */
public class FinishGameScreen extends SimpleTerminalScene {

    private final Game game;

    public FinishGameScreen(Game game) {
        this.game = game;
    }

    @Override
    public Scene play() {


        println(ContentHelper.getContent("/content/finish_screen.txt"));

        return null;
    }
}
