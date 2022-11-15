# Hishab Dice Game
A simple Dice game. This game follow some interesting and fun rules rather than regular dice games. Which makes the game fun and exciting!

## Requirements

- JDK 11
- Maven 3.6.0


## Build

```shell
./mvnw clean install
```

## Run

```shell
java -jar <dice-0.0.1-SNAPSHOT.jar>
```

- Application will be running in http://localhost:8000

## REST API
The REST API to the example app is described below. 

#### You can find a Postman api collection `Dice-Game-APIs.postman_collection.json` at root directory of the project. By exporting it to Postman application you can easily test the APIs.


## Add a new player

`POST 
/game/add-player`

`Sample data: 
{
    "name":"Joy",
    "age":19
}`

## Set winning score

`POST /game/set-winning-score`

`Sample data: 100`

## Start game

`GET /game/start-game`


## Fetch game score

`GET /game/current-score`


## How to play
#### Follow the console for the game result and other instructions you have given using api.
- Add 2-4 players.
- Now you can start the game using `Start game` api.
- The default winning score is 25. You can easily change that using `Set winning score` api.
- During the game you can see the current score using `Fetch game score` api.
- If have fun then you may want To start a new game, right? Let's add some players and start the game.
- For each game you have to add your players for the game.
- Have Fun! Thank you.



