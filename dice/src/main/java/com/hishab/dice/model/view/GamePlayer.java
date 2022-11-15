package com.hishab.dice.model.view;

public class GamePlayer {

    private PlayerDef player;

    private Integer score = 0;

    private Integer currentDice = 0;

    private Integer lastDice = 0;

    private boolean scoreInitiated = false;

    public GamePlayer() {
    }

    public GamePlayer(PlayerDef player) {
        this.player = player;
    }

    public PlayerDef getPlayer() {
        return player;
    }

    public void setPlayer(PlayerDef player) {
        this.player = player;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public void addScore(Integer score) {
        this.score += score;
    }

    public Integer getCurrentDice() {
        return currentDice;
    }

    public void setCurrentDice(Integer currentDice) {
        this.currentDice = currentDice;
    }

    public Integer getLastDice() {
        return lastDice;
    }

    public void setLastDice(Integer lastDice) {
        this.lastDice = lastDice;
    }

    public boolean isScoreInitiated() {
        return scoreInitiated;
    }

    public void setScoreInitiated(boolean scoreInitiated) {
        this.scoreInitiated = scoreInitiated;
    }

}
