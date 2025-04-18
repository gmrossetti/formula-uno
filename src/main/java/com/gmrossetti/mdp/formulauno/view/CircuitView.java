package com.gmrossetti.mdp.formulauno.view;

import com.gmrossetti.mdp.formulauno.circuit.ICircuit;
import com.gmrossetti.mdp.formulauno.circuit.tile.ITile;
import com.gmrossetti.mdp.formulauno.circuit.waypoint.Waypoint;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

/**
 * CircuitView is a JavaFX Pane that represents a racing circuit.
 * It displays the grid points and waypoints of the circuit.
 */
public class CircuitView extends Pane {
    public final int SPACING = 17;
    public final int WIDTH;
    public final int HEIGHT;

    private ArrayList<WaypointView> waypointsViews;

    /**
     * Constructor for CircuitView.
     *
     * @param circuit The ICircuit object representing the circuit.
     */
    public CircuitView(ICircuit circuit) {
        WIDTH = (circuit.getGridWidth() - 1) * SPACING;
        HEIGHT = (circuit.getGridHeight() - 1) * SPACING;

        this.setPrefSize(WIDTH, HEIGHT);

        for (int y = 0; y < circuit.getGridWidth(); y++) {
            for (int x = 0; x < circuit.getGridHeight(); x++){

                ITile tile = circuit.getTile(y,x);

                TileView tileView = new TileView(tile);

                tileView.setTranslateX(this.SPACING * tile.getGridPoint().x);
                tileView.setTranslateY(this.SPACING * tile.getGridPoint().y);

                this.getChildren().add(tileView);
            }
        }

        waypointsViews = new ArrayList<>();

        for (Waypoint wp:
             circuit.getWaypoints()) {
            waypointsViews.add(new WaypointView(wp));
        }

        this.getChildren().addAll(waypointsViews);
    }
}
