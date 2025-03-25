package com.gmrossetti.mdp.view;

import com.gmrossetti.mdp.actor.Circuit;
import com.gmrossetti.mdp.entity.cartesian.CircuitGridPoint;
import com.gmrossetti.mdp.entity.waypoint.Waypoint;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class CircuitView extends Pane {
    public final int SPACING = 17;
    public final int WIDTH;
    public final int HEIGHT;

    private ArrayList<WaypointView> waypointsViews;

    public CircuitView(Circuit circuit) {
        WIDTH = (circuit.getGridWidth() - 1) * SPACING;
        HEIGHT = (circuit.getGridHeight() - 1) * SPACING;

        this.setPrefSize(WIDTH, HEIGHT);

        for (int y = 0; y < circuit.getGridWidth(); y++) {
            for (int x = 0; x < circuit.getGridHeight(); x++){

                CircuitGridPoint circuitGridPoint = circuit.getGridPoint(y,x);

                GridPointView gridPointView = new GridPointView(circuitGridPoint);

                gridPointView.setTranslateX(this.SPACING * circuitGridPoint.x);
                gridPointView.setTranslateY(this.SPACING * circuitGridPoint.y);

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
