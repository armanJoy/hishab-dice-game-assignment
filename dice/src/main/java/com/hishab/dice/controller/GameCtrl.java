package com.hishab.dice.controller;

import com.hishab.dice.model.view.GamePlayer;
import com.hishab.dice.model.view.PlayerDef;
import com.hishab.dice.service.GameService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
@CrossOrigin(origins = "*")
public class GameCtrl {

    @Autowired
    GameService gameService;

    @RequestMapping(method = RequestMethod.POST, value = "/add-player")
    public void addPlayer(@RequestHeader("session") String session, @RequestBody PlayerDef playerDef) {
        gameService.addPlayer(playerDef);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/current-score")
    public List<GamePlayer> getCurrentScore(@RequestHeader("session") String session) {
        return gameService.getCurrentScore();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/start-game")
    public List<GamePlayer> startGame(@RequestHeader("session") String session) {
        return gameService.startGame();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/set-winning-score")
    public void setWinningScore(@RequestHeader("session") String session, @RequestBody Integer winningScore) {
        gameService.setWinningScore(winningScore);
    }

}
