package com.gmrossetti.mdp.core;

import com.gmrossetti.mdp.actor.Car;
import com.gmrossetti.mdp.actor.Circuit;
import com.gmrossetti.mdp.driver.HumanCarDriver;
import com.gmrossetti.mdp.view.ControlsView;
import com.gmrossetti.mdp.view.GameView;
import com.gmrossetti.mdp.view.RaceView;

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

    private RaceView raceView;
    private ControlsView controlsView;
    private GameView view;

    public GameView getView(){
        return view;
    }

    private GameManager(){
        init();
    }

    public void init(){
        Circuit circuit = new Circuit();

        gameState = new GameState(circuit);

        raceView = new RaceView(gameState);
        controlsView = new ControlsView();
        view = new GameView(raceView, controlsView);

        renderer = new Renderer(gameState, view);

        gameLogic = new GameLogic(gameState);

        gameLoop = new GameLoop(gameState, gameLogic, renderer);

        gameLoop.start();

        // TODO: verione dinamica da caricare tramite file
        Car car = new Car(gameState.getCircuit().getRaceStartPoint());
        HumanCarDriver humanCarDriver = new HumanCarDriver(car);

        gameState.addCarDriver(humanCarDriver);
        // ----------------
    }
}
