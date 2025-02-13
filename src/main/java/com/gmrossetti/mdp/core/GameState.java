package com.gmrossetti.mdp.core;

import com.gmrossetti.mdp.driver.CarDriver;
import com.gmrossetti.mdp.actor.Circuit;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    private Circuit circuit;

    public List<CarDriver> getCarDrivers() {
        return carDrivers;
    }

    private List<CarDriver> carDrivers;

    public boolean isRaceActive() {
        return isRaceActive;
    }

    private boolean isRaceActive;

    public GameState(Circuit circuit) {
        this.circuit = circuit;
        carDrivers = new ArrayList<>();
        isRaceActive = true;
    }

    public void addCarDriver(CarDriver carDriver){
        carDrivers.add(carDriver);
    }

    public Circuit getCircuit() {
        return circuit;
    }


}
