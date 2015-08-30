package com.beolnix.trpg.gameplay.scene;

import com.beolnix.trpg.terminal.SimpleTerminal;

/**
 * Scene is a piece of some action in the game.
 * Each action in the game is the Scene.
 * Scene is used to display some information to player and get the player input (optionaly)
 * Created by beolnix on 30/08/15.
 */
public interface Scene {

    /**
     * Launch the scene.
     * @return
     */
    public Scene play();

    /**
     * Returns the terminal used by the scene.
     * @return
     */
    public SimpleTerminal getTerminal();
}
