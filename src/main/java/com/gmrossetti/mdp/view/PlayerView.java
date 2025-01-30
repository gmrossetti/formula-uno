package com.gmrossetti.mdp.view;

import com.gmrossetti.mdp.model.Player;
import com.gmrossetti.mdp.model.Point;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlayerView extends Pane {
    private final int spacing = 20;
    private Set<Line> trailLines = new HashSet<>();
    Rectangle rect;

    public PlayerView(Point position) {

        super(new Pane());

        this.rect = new Rectangle(-13 /2.d, -13 /2.d,13.d, 13.d);
        rect.setFill(Color.YELLOW);
        rect.setRotate(45);

        this.getChildren().add(rect);

        rect.setTranslateX(position.y * spacing);
        rect.setTranslateY(position.x * spacing);
    }

    public void update(Player model){
        rect.setTranslateX(model.getPosition().y * spacing);
        rect.setTranslateY(model.getPosition().x * spacing);

        List<Point> trail = model.getTrail();

        this.getChildren().removeAll(trailLines);

        trailLines.clear();

        if(trail.size() < 2) return;

        for (int i = 1; i < trail.size(); i++) {
            Point pointA = trail.get(i-1);
            Point pointB = trail.get(i);

            Line line = new Line(pointA.y * spacing, pointA.x * spacing, pointB.y * spacing, pointB.x * spacing);

            trailLines.add(line);

            this.getChildren().add(line);
        }
    }
}
