package com.beolnix.trpg.scene;

import com.beolnix.trpg.ContentHelper;

/**
 * Created by beolnix on 30/08/15.
 */
public class GameOverScreen implements Scene {

    @Override
    public Scene play() {
        System.out.println(ContentHelper.getContent("/content/game_over.txt"));
        return null;
    }
}
