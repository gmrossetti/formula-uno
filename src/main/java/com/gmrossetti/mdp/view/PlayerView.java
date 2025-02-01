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
    private final int rectWidth = 13;
    private final int rectHeight = 13;
    private final Set<Line> trailLines = new HashSet<>();
    Rectangle rect;

    public PlayerView(Player player) {

        super(new Pane());

        this.rect = new Rectangle(-rectWidth/2.d,-rectHeight/2.d,rectWidth,rectHeight);
        rect.setFill(Color.YELLOW);
        rect.setRotate(45);

        this.getChildren().add(rect);

        rect.setTranslateX(player.getPosition().x * spacing);
        rect.setTranslateY(player.getPosition().y * spacing);
    }

    public void update(Player model){
        rect.setTranslateX(model.getPosition().x * spacing);
        rect.setTranslateY(model.getPosition().y * spacing);

        List<Point> trail = model.getTrail();

        this.getChildren().removeAll(trailLines);

        trailLines.clear();

        if(trail.size() < 2) return;

        for (int i = 1; i < trail.size(); i++) {
            Point pointA = trail.get(i-1);
            Point pointB = trail.get(i);

            Line line = new Line(pointA.x * spacing, pointA.y * spacing, pointB.x * spacing, pointB.y * spacing);

            trailLines.add(line);

            this.getChildren().add(line);
        }
    }
}
