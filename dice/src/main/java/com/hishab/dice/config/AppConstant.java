package com.hishab.dice.config;

import org.springframework.stereotype.Component;

@Component
public class AppConstant {

    private AppConstant() {
    }

    public static final String APPLICATION_TITLE = "Dice Game";
    public static final String NEW_GAME = "new-game";
    public static final String GAME_RUNNING = "game-running";
    public static final String GAME_END = "game-end";
}
