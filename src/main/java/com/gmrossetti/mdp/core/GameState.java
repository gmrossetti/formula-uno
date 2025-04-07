package com.gmrossetti.mdp.core;

import com.gmrossetti.mdp.circuit.ICircuit;
import com.gmrossetti.mdp.driver.HumanDriverFactory;
import com.gmrossetti.mdp.driver.IDriver;

import java.util.*;
import java.util.stream.Collectors;

public class GameState {
    final IDriver humanDriver;

    public boolean isRaceActive() {
        return !getCarDriversStillPlaying().isEmpty() && getCarDriversStillPlaying().contains(getHumanCarDriver()) ||
                getLeaderboard().isEmpty();
    }
    private final ICircuit circuit;
    public ICircuit getCircuit() {
        return circuit;
    }

    final private Set<IDriver> carDrivers;

    public Leaderboard getLeaderboard() {
        return leaderboard;
    }

    final private Leaderboard leaderboard;

    public Set<IDriver> getCarDrivers() {
        return carDrivers;
    }

    public Set<IDriver> getCarDriversStillPlaying() {
        return carDrivers.stream()
                .filter(IDriver::hasActiveWaypoint)
                .filter(carDriver -> !leaderboard.containsCarDriver(carDriver))
                .collect(Collectors.toSet());
    }

    public GameState(ICircuit circuit) {
        this.circuit = circuit;
        carDrivers = new HashSet<>();
        leaderboard = new Leaderboard();

        humanDriver = HumanDriverFactory.build(circuit);
        carDrivers.add(humanDriver);
    }

    public IDriver getHumanCarDriver() {
        return humanDriver;
    }

    public void addCarDriver(IDriver driver){
        if(!isRaceActive()){
            throw new IllegalStateException("Race ended.");
        }

        carDrivers.add(driver);
    }

    public void addCarDriver(List<IDriver> drivers){
        for (IDriver driver:
                drivers) {
            addCarDriver(driver);
        }
    }
}
