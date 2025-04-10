package com.gmrossetti.mdp.core;

import com.gmrossetti.mdp.circuit.ICircuit;
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

    final private List<IDriver> drivers;

    public Leaderboard getLeaderboard() {
        return leaderboard;
    }

    final private Leaderboard leaderboard;

    public List<IDriver> getDrivers() {
        return drivers;
    }

    public List<IDriver> getDriversStillPlaying() {
        return drivers.stream()
                .filter(IDriver::hasActiveWaypoint)
                .filter(drivers -> !leaderboard.containsDriver(drivers))
                .collect(Collectors.toList());
    }

    public GameState(ICircuit circuit, IDriver humanDriver, Collection<IDriver> botDrivers) {
        this.circuit = circuit;
        drivers = new ArrayList<>();
        leaderboard = new Leaderboard();

        // aggiunge il driver umano per primo in modo da garantire che sia il primo a scegliere la mossa
        this.humanDriver = humanDriver;
        drivers.add(humanDriver);

        drivers.addAll(botDrivers);
    }

    public IDriver getHumanDriver() {
        return humanDriver;
    }
}
