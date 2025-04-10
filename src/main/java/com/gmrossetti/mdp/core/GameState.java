package com.gmrossetti.mdp.core;

import com.gmrossetti.mdp.circuit.ICircuit;
import com.gmrossetti.mdp.driver.HumanDriverFactory;
import com.gmrossetti.mdp.driver.IDriver;
import com.gmrossetti.mdp.leaderboard.Leaderboard;

import java.util.*;
import java.util.stream.Collectors;

public class GameState {
    final IDriver humanDriver;

    public boolean isRaceActive() {
        return !getDriversStillPlaying().isEmpty() && getDriversStillPlaying().contains(getHumanDriver()) ||
                getLeaderboard().isEmpty();
    }
    private final ICircuit circuit;
    public ICircuit getCircuit() {
        return circuit;
    }

    final private Set<IDriver> drivers;

    public Leaderboard getLeaderboard() {
        return leaderboard;
    }

    final private Leaderboard leaderboard;

    public Set<IDriver> getDrivers() {
        return drivers;
    }

    public Set<IDriver> getDriversStillPlaying() {
        return drivers.stream()
                .filter(IDriver::hasActiveWaypoint)
                .filter(drivers -> !leaderboard.containsDriver(drivers))
                .collect(Collectors.toSet());
    }

    public GameState(ICircuit circuit) {
        this.circuit = circuit;
        drivers = new HashSet<>();
        leaderboard = new Leaderboard();

        // TODO: pass all drivers to the constructor

        humanDriver = HumanDriverFactory.build(circuit);
        drivers.add(humanDriver);
    }

    public IDriver getHumanDriver() {
        return humanDriver;
    }

    public void addDriver(IDriver driver){
        if(!isRaceActive()){
            throw new IllegalStateException("Race ended.");
        }

        drivers.add(driver);
    }

    public void addDriver(List<IDriver> drivers){
        for (IDriver driver:
                drivers) {
            addDriver(driver);
        }
    }
}
