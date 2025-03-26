package com.gmrossetti.mdp.core;

import com.gmrossetti.mdp.actor.Car;
import com.gmrossetti.mdp.actor.Circuit;
import com.gmrossetti.mdp.driver.*;
import com.gmrossetti.mdp.parser.GameParseObject;
import com.gmrossetti.mdp.parser.GameParser;
import com.gmrossetti.mdp.strategy.IStrategy;
import com.gmrossetti.mdp.strategy.StrategyFactory;
import com.gmrossetti.mdp.strategy.StrategyParameters;
import com.gmrossetti.mdp.view.GameView;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.StageStyle;

public class GameManager {
    private static GameManager instance = null;
    public static GameManager getInstance(){
        if(instance == null){
            instance = new GameManager();
        }

        return instance;
    }

    private Renderer renderer;
    private GameLoop gameLoop;
    private GameState gameState;
    private GameLogic gameLogic;

    private GameView view;

    public Scene getGameScene() {
        return gameScene;
    }

    private final Scene gameScene;

    private GameManager(){
        init();

        gameScene = new Scene(view);
    }

    public void init(){

        GameParseObject gameParserObject = GameParser.parseGameConfigJson("game-config1");

        Circuit circuit = gameParserObject.getCircuit();

        gameState = new GameState(circuit);

        view = new GameView(gameState);

        renderer = new Renderer(gameState, view);

        gameLogic = new GameLogic(gameState);

        gameLoop = new GameLoop(gameLogic, renderer);

        gameLoop.start();

        Car car = new Car(gameState.getCircuit().getRaceStartPoint());
        HumanCarDriver humanCarDriver = new HumanCarDriver(car, gameState.getCircuit());

        gameState.addCarDriver(humanCarDriver);

        for (StrategyParameters strategyParameters:
                gameParserObject.getStrategyParameters()) {
            Car botCar = new Car(gameState.getCircuit().getRaceStartPoint());
            IStrategy strategy = StrategyFactory.buildStrategy(strategyParameters);

            BotCarDriver botCarDriver = new BotCarDriver(botCar,gameState.getCircuit(), strategy);

            gameState.addCarDriver(botCarDriver);
        }
    }

    public void reset(){
        init();

        gameScene.setRoot(view);
    }

    public void checkIfGameEnded() {
        if(gameState.isRaceActive()) return;

        gameLoop.stop();

        Leaderboard leaderBoard = gameState.getLeaderboard();

        LeaderboardEntry humanCarDriverLeaderboardEntry = leaderBoard.getLeaderboardEntry(gameState.getHumanCarDriver());

        String title;
        String message;

        if(humanCarDriverLeaderboardEntry.isDisqualified()){
            title = "Squalificato!";
            message = "Sei uscito dalla pista!";
        } else {
            title = "Complimenti!";
            message = "Hai concluso la gara in " + leaderBoard.getPosition(gameState.getHumanCarDriver()) + "^ posizione!";
        }

        message += "\nIl gioco è stato resettato.";

        showAlert(title, message);
        reset();
    }

    public void showAlert(String title, String message){
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle(title);
        dialog.setHeaderText(message);

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);

        dialog.initStyle(StageStyle.UNDECORATED);

        Platform.runLater(dialog::showAndWait);
    }
}
