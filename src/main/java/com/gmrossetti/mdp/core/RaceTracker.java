package com.gmrossetti.mdp.core;

import com.gmrossetti.mdp.actor.Car;
import com.gmrossetti.mdp.driver.BotCarDriver;
import com.gmrossetti.mdp.driver.CarDriver;
import com.gmrossetti.mdp.entity.GridLine;
import com.gmrossetti.mdp.entity.GridPoint;
import com.gmrossetti.mdp.entity.Waypoint;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
/*

public class RaceTracker {
    private final List<Waypoint> waypoints;
    private final Map<CarDriver, Waypoint> carDriverWaypoints;
    private final Set<CarDriver> carDrivers;

    public RaceTracker(GameState gameState) {
        this.carDrivers = gameState.getCarDrivers();
        this.carDriverWaypoints = new HashMap<>();

        waypoints = gameState.getCircuit().();

        Waypoint firstWaypoint = waypoints.get(0);

        for (CarDriver carDriver:
                carDrivers) {
            carDriverWaypoints.put(carDriver,firstWaypoint);
            carDriver.setWaypointTarget(firstWaypoint);
        }
    }

    public void updateWaypointTargets(){
        for (CarDriver carDriver:
                carDrivers) {
            Waypoint waypoint = carDriverWaypoints.get(carDriver);

            if(waypoint.isWithinRadius(carDriver)){
                int newWaypointIndex = waypoints.indexOf(waypoint) + 1;

                if(newWaypointIndex >= waypoints.size()){
                    throw new RuntimeException("Player has reached end");
                }

                carDriverWaypoints.put(carDriver, waypoints.get(newWaypointIndex));
            }
        }
    }
}*/
