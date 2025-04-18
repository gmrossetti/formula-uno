package com.gmrossetti.mdp.formulauno.circuit;

import com.gmrossetti.mdp.formulauno.cartesian.GridPoint;
import com.gmrossetti.mdp.formulauno.circuit.tile.ITile;
import com.gmrossetti.mdp.formulauno.circuit.tile.Tile;
import com.gmrossetti.mdp.formulauno.circuit.waypoint.BoundaryWaypoint;
import com.gmrossetti.mdp.formulauno.circuit.waypoint.MidWaypoint;
import com.gmrossetti.mdp.formulauno.circuit.waypoint.Waypoint;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CircuitTest {

    @Test
    void testGetWaypoints() {
        ITile[][] grid = new ITile[5][5];
        List<Waypoint> waypoints = Arrays.asList(
            new BoundaryWaypoint(new GridPoint(1, 1), 1, 3, BoundaryWaypoint.Type.START),
            new MidWaypoint(new GridPoint(2, 2), 2)
        );
        Circuit circuit = new Circuit(grid, waypoints);

        assertEquals(waypoints, circuit.getWaypoints());
    }

    @Test
    void testGetTileWithinBounds() {
        ITile[][] grid = new ITile[5][5];
        grid[2][3] = new Tile(new GridPoint(3, 2), true, null);

        List<Waypoint> waypoints = Arrays.asList(
                new BoundaryWaypoint(new GridPoint(1, 1), 1, 3, BoundaryWaypoint.Type.START),
                new MidWaypoint(new GridPoint(2, 2), 2)
        );

        Circuit circuit = new Circuit(grid, waypoints);

        assertNotNull(circuit);

        ITile tile = circuit.getTile(3, 2);
        assertNotNull(tile);
    }

    @Test
    void testGetTileOutOfBounds() {
        ITile[][] grid = new ITile[5][5];

        List<Waypoint> waypoints = Arrays.asList(
                new BoundaryWaypoint(new GridPoint(1, 1), 1, 3, BoundaryWaypoint.Type.START),
                new MidWaypoint(new GridPoint(2, 2), 2)
        );

        Circuit circuit = new Circuit(grid, waypoints);

        ITile tile = circuit.getTile(10, 10);
        assertNotNull(tile);
    }

    @Test
    void testGetRaceStartPoint() {
        ITile[][] grid = new ITile[5][5];

        grid[1][1] = new Tile(new GridPoint(1, 1), true, null);

        List<Waypoint> waypoints = Arrays.asList(
                new BoundaryWaypoint(new GridPoint(1, 1), 2, 3, BoundaryWaypoint.Type.START),
                new MidWaypoint(new GridPoint(2, 2), 2),
                new BoundaryWaypoint(new GridPoint(1, 1), 2, 3, BoundaryWaypoint.Type.FINISH)
        );

        Circuit circuit = new Circuit(grid, waypoints);

        ITile raceStartPoint = circuit.getRaceStartPoint();
        assertNotNull(raceStartPoint);
        assertEquals(new GridPoint(1, 1), raceStartPoint.getGridPoint());
    }

    @Test
    void testGetGridDimensions() {
        ITile[][] grid = new ITile[4][6];

        List<Waypoint> waypoints = Arrays.asList(
                new BoundaryWaypoint(new GridPoint(1, 1), 2, 3, BoundaryWaypoint.Type.START),
                new MidWaypoint(new GridPoint(2, 2), 2),
                new BoundaryWaypoint(new GridPoint(1, 1), 2, 3, BoundaryWaypoint.Type.FINISH)
        );

        Circuit circuit = new Circuit(grid, waypoints);

        assertEquals(6, circuit.getGridWidth());
        assertEquals(4, circuit.getGridHeight());
    }
}
