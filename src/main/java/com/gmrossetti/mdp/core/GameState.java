package com.gmrossetti.mdp.core;

import com.gmrossetti.mdp.driver.CarDriver;
import com.gmrossetti.mdp.circuit.ICircuit;
import com.gmrossetti.mdp.driver.HumanCarDriver;

import java.util.*;
import java.util.stream.Collectors;

public class GameState {
    public boolean isRaceActive() {
        return !getCarDriversStillPlaying().isEmpty() && getCarDriversStillPlaying().contains(getHumanCarDriver()) ||
                getLeaderboard().isEmpty();
    }
    private final ICircuit circuit;
    public ICircuit getCircuit() {
        return circuit;
    }

    final private Set<CarDriver> carDrivers;

    public Leaderboard getLeaderboard() {
        return leaderboard;
    }

    final private Leaderboard leaderboard;

    public Set<CarDriver> getCarDrivers() {
        return carDrivers;
    }

    public Set<CarDriver> getCarDriversStillPlaying() {
        return carDrivers.stream()
                .filter(CarDriver::hasActiveWaypoint)
                .filter(carDriver -> !leaderboard.containsCarDriver(carDriver))
                .collect(Collectors.toSet());
    }

    public GameState(ICircuit circuit) {
        this.circuit = circuit;
        carDrivers = new HashSet<>();
        leaderboard = new Leaderboard();
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
        if(!isRaceActive()){
            throw new IllegalStateException("Race ended.");
        }

        carDrivers.add(carDriver);
    }
}
