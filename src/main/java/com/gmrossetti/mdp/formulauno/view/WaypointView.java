package com.gmrossetti.mdp.formulauno.view;

import com.gmrossetti.mdp.formulauno.circuit.waypoint.BoundaryWaypoint;
import com.gmrossetti.mdp.formulauno.circuit.waypoint.MidWaypoint;
import com.gmrossetti.mdp.formulauno.circuit.waypoint.Waypoint;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class WaypointView extends Pane {
    public final int SPACING = 17;

    public WaypointView(Waypoint waypoint){

        if(waypoint instanceof MidWaypoint midWaypoint){

            Circle circle = new Circle(SPACING * midWaypoint.getCenter().x, SPACING * midWaypoint.getCenter().y,
                    SPACING * midWaypoint.getRadius());

            circle.setStroke(Color.GOLD);

            circle.setFill(Color.TRANSPARENT);

            circle.setStrokeWidth(1.5);

            Circle center = new Circle(SPACING * midWaypoint.getCenter().x, SPACING * midWaypoint.getCenter().y,
                    SPACING * 0.3);
            center.setFill(Color.web("#FFaa00"));

            Group group = new Group(circle, center);

            this.getChildren().add(group);
        } else if (waypoint instanceof BoundaryWaypoint boundaryWaypoint) {
            Rectangle rectangle = new Rectangle(
                    SPACING * (boundaryWaypoint.getCenter().x  - boundaryWaypoint.getWidth() / 2.f),
                    SPACING * (boundaryWaypoint.getCenter().y - boundaryWaypoint.getHeight() / 2.f),
                    SPACING * boundaryWaypoint.getWidth(),
                    SPACING * boundaryWaypoint.getHeight());

            rectangle.setFill(Color.TRANSPARENT);

            if(boundaryWaypoint.getType() == BoundaryWaypoint.Type.START){
                rectangle.setFill(Color.GREEN);
                rectangle.setStroke(Color.GREEN);
            } else {
                rectangle.setFill(Color.BLACK);
                rectangle.setStroke(Color.BLACK);
            }

            rectangle.setStrokeWidth(2);
            this.getChildren().add(rectangle);
        }
    }
}
