package com.beolnix.trpg.gameplay.scene;

import com.beolnix.trpg.terminal.SimpleTerminal;

/**
 * Created by beolnix on 30/08/15.
 */
public interface Scene {
    public Scene play();
    public SimpleTerminal getTerminal();
}
