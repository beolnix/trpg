package com.beolnix.trpg.gameplay.scene.impl;

import com.beolnix.trpg.utils.ContentHelper;
import com.beolnix.trpg.gameplay.scene.Scene;
import com.beolnix.trpg.gameplay.scene.SimpleTerminalScene;

/**
 * Created by beolnix on 30/08/15.
 */
public class GameOverScreen extends SimpleTerminalScene {

    @Override
    public Scene play() {
        print(ContentHelper.getContent("/content/game_over.txt"));
        return null;
    }
}
