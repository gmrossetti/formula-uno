package com.gmrossetti.mdp.core;

import com.gmrossetti.mdp.driver.CarDriver;
import com.gmrossetti.mdp.actor.Circuit;
import com.gmrossetti.mdp.driver.HumanCarDriver;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    private Circuit circuit;
    public Circuit getCircuit() {
        return circuit;
    }

    private List<CarDriver> carDrivers;
    public List<CarDriver> getCarDrivers() {
        return carDrivers;
    }

    private boolean isRaceActive;
    public boolean isRaceActive() {
        return isRaceActive;
    }

    public GameState(Circuit circuit) {
        this.circuit = circuit;
        carDrivers = new ArrayList<>();
        isRaceActive = true;
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

    public void addCarDriver(CarDriver carDriver){
        carDrivers.add(carDriver);
    }
}
