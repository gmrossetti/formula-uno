package com.gmrossetti.mdp.formulauno.core;

import com.gmrossetti.mdp.formulauno.circuit.ICircuit;
import com.gmrossetti.mdp.formulauno.driver.IDriver;
import com.gmrossetti.mdp.formulauno.leaderboard.Leaderboard;

import java.util.*;
import java.util.stream.Collectors;

/**
 * GameState class represents the current state of the game.
 * It contains information about the circuit, drivers, and leaderboard.
 */
public class GameState {
    /**
     * Returns the human driver of the game.
     *
     * @return The human driver.
     */
    final IDriver humanDriver;

    /**
     * Returns the human driver of the game.
     *
     * @return The human driver.
     */
    public boolean isRaceActive() {
        return !getDriversStillPlaying().isEmpty() && getDriversStillPlaying().contains(getHumanDriver()) ||
                getLeaderboard().isEmpty();
    }
    private final ICircuit circuit;

    /**
     * Returns the circuit of the game.
     *
     * @return The circuit.
     */
    public ICircuit getCircuit() {
        return circuit;
    }

    /**
     * Returns the circuit of the game.
     *
     * @return The circuit.
     */
    final private List<IDriver> drivers;

    /**
     * Returns the leaderboard of the game.
     *
     * @return The leaderboard.
     */
    public Leaderboard getLeaderboard() {
        return leaderboard;
    }

    final private Leaderboard leaderboard;

    /**
     * Returns a list of all drivers in the game.
     *
     * @return List of all drivers.
     */
    public List<IDriver> getDrivers() {
        return drivers;
    }

    /**
     * Returns a list of drivers that are still playing and not in the leaderboard.
     *
     * @return List of drivers still playing.
     */
    public List<IDriver> getDriversStillPlaying() {
        return drivers.stream()
                .filter(IDriver::hasActiveWaypoint)
                .filter(drivers -> !leaderboard.containsDriver(drivers))
                .collect(Collectors.toList());
    }

    /**
     * Constructor for GameState.
     *
     * @param circuit The circuit of the game.
     * @param humanDriver The human driver.
     * @param botDrivers The list of bot drivers.
     */
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
