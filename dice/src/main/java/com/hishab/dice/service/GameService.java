package com.hishab.dice.service;

import com.hishab.dice.model.view.GamePlayer;
import com.hishab.dice.model.view.PlayerDef;
import java.util.List;

public interface GameService {

    void addPlayer(PlayerDef newPlayer);

    List<GamePlayer> startGame();

    List<GamePlayer> getCurrentScore();

    void setWinningScore(Integer winningScore);

}
