package com.gmrossetti.mdp.view;

import com.gmrossetti.mdp.entity.GridPoint;
import com.gmrossetti.mdp.actor.Car;
import javafx.scene.Group;
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
    private final Rectangle pivotRect;
    private final Group trailSegments;

    public CarView(Car car){
        shape = new Rectangle(-RECT_WIDTH/2.d,-RECT_HEIGHT/2.d,RECT_WIDTH,RECT_HEIGHT);
        shape.setFill(Color.YELLOW);
        shape.setRotate(45);

        pivotRect = new Rectangle(-RECT_WIDTH/2.d,-RECT_HEIGHT/2.d,RECT_WIDTH,RECT_HEIGHT);
        Color color = new Color(Color.YELLOW.getRed(), Color.YELLOW.getGreen(), Color.YELLOW.getBlue(), 0.6);

        pivotRect.setFill(color);
        pivotRect.setRotate(45);

        trailSegments = new Group();

        update(car);

        this.getChildren().add(shape);

        this.getChildren().add(pivotRect);

        this.getChildren().add(trailSegments);
    }

    public void update(Car car){
        shape.setTranslateX(car.getPosition().x * SPACING);
        shape.setTranslateY(car.getPosition().y * SPACING);

        updateTrailSegments(car);
        updatePivotPoint(car);
    }

    private void updateTrailSegments(Car car){

        trailSegments.getChildren().clear();

        final List<GridPoint> carTrail = car.getTrail();

        if(carTrail.size() < 2) return;

        for (int i = 0; i < carTrail.size(); i++) {
            if(i == 0) continue;

            GridPoint gpA = carTrail.get(i - 1);
            GridPoint gpB = carTrail.get(i);

            Line trailSegment = new Line(gpA.x * SPACING, gpA.y * SPACING, gpB.x * SPACING, gpB.y * SPACING);
            trailSegment.setStroke(Color.YELLOW);
            trailSegment.setStrokeWidth(2);

            trailSegments.getChildren().add(trailSegment);
        }
    }

    private void updatePivotPoint(Car car){
        pivotRect.setTranslateX(car.getPivot().x * SPACING);
        pivotRect.setTranslateY(car.getPivot().y * SPACING);
    }
}
