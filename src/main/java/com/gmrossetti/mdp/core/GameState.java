package com.gmrossetti.mdp.core;

import com.gmrossetti.mdp.driver.CarDriver;
import com.gmrossetti.mdp.actor.Circuit;
import com.gmrossetti.mdp.driver.HumanCarDriver;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    private Circuit circuit;

    public List<CarDriver> getCarDrivers() {
        return carDrivers;
    }

    public HumanCarDriver getHumanCarDriver() {
        for (CarDriver carDriver:
                carDrivers) {
            if(carDriver instanceof HumanCarDriver){
                return (HumanCarDriver) carDriver;
            }
        }

        return null;
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
