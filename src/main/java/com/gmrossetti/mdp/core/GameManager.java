package com.gmrossetti.mdp.core;

import com.gmrossetti.mdp.actor.Car;
import com.gmrossetti.mdp.actor.Circuit;
import com.gmrossetti.mdp.driver.BotCarDriver;
import com.gmrossetti.mdp.view.ControlsView;
import com.gmrossetti.mdp.view.GameView;
import com.gmrossetti.mdp.view.RaceView;

public class GameManager {
    private static GameManager instance = null;

    private GameManager(){
        init();
    }

    public static GameManager getInstance(){
        if(instance == null){
            instance = new GameManager();
        }

        return instance;
    }

    private Renderer renderer;
    private GameLoop gameLoop;
    private GameState gameState;
//    private GameLogic gameLogic;

    private RaceView raceView;
    private ControlsView controlsView;

    private GameView view;

    public GameView getView(){
        return view;
    }

    public void init(){
        Circuit circuit = new Circuit();

        gameState = new GameState(circuit);

        raceView = new RaceView(gameState);

        controlsView = new ControlsView();

        view = new GameView(raceView, controlsView);

        renderer = new Renderer(gameState, view);
        gameLoop = new GameLoop(renderer);

        //        gameLogic = new GameLogic();

        gameLoop.start();

        // TODO: da rimuovere... Solo per scopo di test
        addCarDriver();
    }

    // TODO: rimuovere. Serve solo per test
    private void addCarDriver(){

        Car car = new Car(gameState.getCircuit().getRaceStartPoint());
        BotCarDriver botCarDriver = new BotCarDriver(car);

//        gameState.getCarDrivers();

        this.gameState.addCarDriver(botCarDriver);
    }
}
