package com.gmrossetti.mdp.core;

import com.gmrossetti.mdp.driver.CarDriver;
import com.gmrossetti.mdp.actor.Circuit;
import com.gmrossetti.mdp.driver.HumanCarDriver;
import com.gmrossetti.mdp.entity.GridLine;

import java.util.*;

public class GameState {
    private Circuit circuit;
    public Circuit getCircuit() {
        return circuit;
    }

    final private Map<CarDriver, DriverMoveValidator.MoveResult> carDriversMoveResult;

    public Set<CarDriver> getCarDrivers() {
        return carDriversMoveResult.keySet();
    }

    public DriverMoveValidator.MoveResult getCarDriverMoveResult(CarDriver carDriver){
        DriverMoveValidator.MoveResult moveResult = carDriversMoveResult.get(carDriver);

        if(moveResult == null){
            throw new IllegalArgumentException("carDriver provided does not exist.");
        }

        return moveResult;
    }

    public Map<CarDriver, DriverMoveValidator.MoveResult> getCarDriverMoveResults(){
        return carDriversMoveResult;
    }

    private boolean isRaceActive;
    public boolean isRaceActive() {
        return isRaceActive;
    }

    public GameState(Circuit circuit) {
        this.circuit = circuit;
        carDriversMoveResult = new HashMap<>();
        isRaceActive = true;
    }

    public HumanCarDriver getHumanCarDriver() {
        for (CarDriver carDriver:
                getCarDrivers()) {
            if(carDriver instanceof HumanCarDriver){
                return (HumanCarDriver) carDriver;
            }
        }

        return null;
    }

    public void addCarDriver(CarDriver carDriver){
        carDriversMoveResult.put(carDriver, DriverMoveValidator.MoveResult.OK);
    }

    public void updateCarDriverState(CarDriver carDriver, DriverMoveValidator.MoveResult moveResult){
        if(!getCarDrivers().contains(carDriver)){
            throw new IllegalArgumentException("carDriver provided does not exist.");
        }

        carDriversMoveResult.put(carDriver,moveResult);
    }
}
