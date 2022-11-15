package com.hishab.dice.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hishab.dice.config.AppConstant;
import com.hishab.dice.model.view.Dice;
import com.hishab.dice.model.view.GamePlayer;
import com.hishab.dice.model.view.PlayerDef;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {

    private static String gameState = AppConstant.NEW_GAME;
    private static List<GamePlayer> players = new ArrayList<>();
    private static Integer winningScore = 25;

    @Override
    public void addPlayer(PlayerDef newPlayer) {

        if (!AppConstant.GAME_RUNNING.equals(gameState) && players.size() < 4) {
            GamePlayer newGamePlayer = new GamePlayer(newPlayer);
            players.add(newGamePlayer);

            System.out.println("New Player Added. Name: " + newPlayer.getName() + ", Age: " + newPlayer.getAge());

        } else if (AppConstant.GAME_RUNNING.equals(gameState)) {
            System.out.println("Player cannot be added while a game is running");
        }

        if (players.size() == 4) {
            System.out.println("Only 4 players can be added");
        }
    }

    @Override
    public List<GamePlayer> startGame() {
        List<GamePlayer> gamePlayers = new ArrayList<>();
        if (!AppConstant.GAME_RUNNING.equals(gameState) && this.players.size() >= 2) {
            System.out.println("\n\nNew Game Started...\n\n");

            gameState = AppConstant.GAME_RUNNING;
            while (AppConstant.GAME_RUNNING.equals(gameState)) {
                for (int i = 0; i < players.size(); i++) {
                    GamePlayer player = players.get(i);

                    rollDiceForPlayer(player);

                    if (player.getScore() >= winningScore) {
                        System.out.println(player.getPlayer().getName() + "is the WINNER!\n" + player.getPlayer().getName() + "'s score is " + player.getScore());

                        gameState = AppConstant.GAME_END;
                        gamePlayers = new ArrayList<>(this.players);
                        this.players.clear();
                        break;
                    }
                }
                System.out.println("\n");
            }

        } else {
            if (AppConstant.GAME_RUNNING.equals(gameState)) {
                System.out.println("Game is running...");
            }

            if (this.players.size() < 2) {
                System.out.println("Insufficient player. Please add at lease 2 player");
            }

        }

        return gamePlayers;
    }

    @Override
    public List<GamePlayer> getCurrentScore() {
        return players;
    }

    public void rollDiceForPlayer(GamePlayer player) {
        Dice dice = rollDice();

        if (dice.getScore() != null) {
            player.setLastDice(player.getCurrentDice());
            player.setCurrentDice(dice.getScore());

            if (!player.isScoreInitiated() && dice.getScore() != 4 && player.getLastDice() == 6) {
                player.setScoreInitiated(true);
            }

            if (player.isScoreInitiated()) {

                int scoreValue = (dice.getScore() == 4) ? -4 : dice.getScore();
                player.addScore(scoreValue);

            }

            logPlayerDiceThrow(player);

            if (player.getScore() < winningScore && dice.getScore() == 6) {
                rollDiceForPlayer(player);
            }
        } else {
            rollDiceForPlayer(player);
        }

    }

    public Dice rollDice() {

        String diceScore = "";
        String USER_AGENT = "Mozilla/5.0";

        String url = "http://developer-test.hishab.io/api/v1/roll-dice";
        try {
            URL obj = new URL(url);

            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);
            int responseCode = con.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;

                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);

                }

                in.close();

                diceScore = response.toString();

            }

        } catch (MalformedURLException ex) {
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println(ex);
        }

        Gson gson = new Gson();

        Dice dice = (!diceScore.isEmpty()) ? gson.fromJson(diceScore, Dice.class) : new Dice();

        return dice;
    }

    private void logPlayerDiceThrow(GamePlayer gamePlayer) {
        System.out.println("Player Name: " + gamePlayer.getPlayer().getName() + ", Total Score: " + gamePlayer.getScore() + ", Current Value of Dice: " + gamePlayer.getCurrentDice());
    }

    @Override
    public void setWinningScore(Integer winningScore) {
        GameServiceImpl.winningScore = winningScore;
        System.out.println("Winning score set to " + winningScore);
    }

//    public List<Object> convertObjectList(List<Object> data) {
//        Gson jsonParser = new Gson();
//        String dataJson = jsonParser.toJson(data);
//        List<Object> convertedData = (List<Object>) jsonParser.fromJson(dataJson, new TypeToken<List<Object>>() {
//        }.getType());
//        return convertedData;
//    }
//
//    public Object convertObject(Object data) {
//        Gson jsonParser = new Gson();
//        String dataJson = jsonParser.toJson(data);
//        Object convertedData = jsonParser.fromJson(dataJson, Object.class);
//        return convertedData;
//    }
}
