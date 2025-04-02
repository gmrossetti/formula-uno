package com.gmrossetti.mdp.view;

import com.gmrossetti.mdp.circuit.ICircuit;
import com.gmrossetti.mdp.circuit.tile.ITile;
import com.gmrossetti.mdp.circuit.waypoint.Waypoint;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class CircuitView extends Pane {
    public final int SPACING = 17;
    public final int WIDTH;
    public final int HEIGHT;

    private ArrayList<WaypointView> waypointsViews;

    public CircuitView(ICircuit circuit) {
        WIDTH = (circuit.getGridWidth() - 1) * SPACING;
        HEIGHT = (circuit.getGridHeight() - 1) * SPACING;

        this.setPrefSize(WIDTH, HEIGHT);

        for (int y = 0; y < circuit.getGridWidth(); y++) {
            for (int x = 0; x < circuit.getGridHeight(); x++){

                ITile tile = circuit.getTile(y,x);

                GridPointView gridPointView = new GridPointView(tile);

                gridPointView.setTranslateX(this.SPACING * tile.getGridPoint().x);
                gridPointView.setTranslateY(this.SPACING * tile.getGridPoint().y);

                this.getChildren().add(gridPointView);
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
