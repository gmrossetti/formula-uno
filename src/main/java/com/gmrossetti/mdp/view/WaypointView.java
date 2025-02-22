package com.gmrossetti.mdp.view;

import com.gmrossetti.mdp.entity.Waypoint;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class WaypointView extends Pane {
    public final int SPACING = 17;

    public WaypointView(Waypoint waypoint){
        Circle circle = new Circle(SPACING * waypoint.getCenter().x, SPACING * waypoint.getCenter().y,
                SPACING * waypoint.getRadius());

        circle.setStroke(Color.GOLD);

        circle.setFill(Color.web("#00000000"));

        circle.setStrokeWidth(1.5);

        Circle center = new Circle(SPACING * waypoint.getCenter().x, SPACING * waypoint.getCenter().y,
                SPACING * 0.3);
        center.setFill(Color.web("#FFaa00"));

        Group group = new Group(circle, center);

        this.getChildren().add(group);
    }
}
