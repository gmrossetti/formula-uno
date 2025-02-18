package com.gmrossetti.mdp.core;

import com.gmrossetti.mdp.actor.Car;
import com.gmrossetti.mdp.actor.Circuit;
import com.gmrossetti.mdp.driver.HumanCarDriver;
import com.gmrossetti.mdp.view.GameView;

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

    public GameView getView(){
        return view;
    }

    private GameManager(){
        init();
    }

    public void init(){
        Circuit circuit = new Circuit();

        gameState = new GameState(circuit);

        view = new GameView(gameState);

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
