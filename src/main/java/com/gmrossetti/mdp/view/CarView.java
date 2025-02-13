package com.gmrossetti.mdp.view;

import com.gmrossetti.mdp.model.Point;
import com.gmrossetti.mdp.actor.Car;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.List;

public class CarView extends Pane {
    public final int SPACING = 17;
    public final int RECT_WIDTH = 13;
    public final int RECT_HEIGHT = 13;

    private final Shape shape;

    public CarView(Car car){
        shape = new Rectangle(-RECT_WIDTH/2.d,-RECT_HEIGHT/2.d,RECT_WIDTH,RECT_HEIGHT);
        shape.setFill(Color.YELLOW);
        shape.setRotate(45);

        update(car);

        this.getChildren().add(shape);
    }

    public void update(Car car){
        shape.setTranslateX(car.getPosition().x * SPACING);
        shape.setTranslateY(car.getPosition().y * SPACING);

        addTrailLine(car);
    }

    private void addTrailLine(Car car){
        final List<Point> carTrail = car.getTrail();

        if(carTrail.size() < 2) return;

        Point pointA = carTrail.get(carTrail.size() - 2);
        Point pointB = carTrail.get(carTrail.size() - 1);

        Line trailSegment = new Line(pointA.x * SPACING, pointA.y * SPACING, pointB.x * SPACING, pointB.y * SPACING);
        trailSegment.setStroke(Color.YELLOW);

        this.getChildren().add(trailSegment);
    }
}
