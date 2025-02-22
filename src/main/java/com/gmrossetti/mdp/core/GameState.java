package com.gmrossetti.mdp.core;

import com.gmrossetti.mdp.driver.CarDriver;
import com.gmrossetti.mdp.actor.Circuit;
import com.gmrossetti.mdp.driver.HumanCarDriver;

import java.util.*;
import java.util.stream.Collectors;

public class GameState {
    private boolean isRaceActive;
    public boolean isRaceActive() {
        return isRaceActive;
    }
    private Circuit circuit;
    public Circuit getCircuit() {
        return circuit;
    }

    final private Map<CarDriver, DriverMoveValidator.MoveResult> carDriversMoveResult;

    public Set<CarDriver> getCarDrivers() {
        return carDriversMoveResult.keySet();
    }

    public Set<CarDriver> getCarDriversStillPlaying() {
        return carDriversMoveResult.keySet().stream()
                .filter(carDriver -> carDriversMoveResult.get(carDriver) == DriverMoveValidator.MoveResult.OK)
                .collect(Collectors.toSet());
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
        if(!isRaceActive){
            throw new IllegalStateException("Race ended.");
        }

        carDriversMoveResult.put(carDriver, DriverMoveValidator.MoveResult.OK);
    }

    public void updateCarDriverState(CarDriver carDriver, DriverMoveValidator.MoveResult moveResult){
        if(!isRaceActive){
            throw new IllegalStateException("Race ended.");
        }

        if(!getCarDrivers().contains(carDriver)){
            throw new IllegalArgumentException("carDriver provided does not exist.");
        }

        carDriversMoveResult.put(carDriver,moveResult);

        if(carDriver instanceof HumanCarDriver && moveResult != DriverMoveValidator.MoveResult.OK){
            isRaceActive = false;
        }
    }
}
