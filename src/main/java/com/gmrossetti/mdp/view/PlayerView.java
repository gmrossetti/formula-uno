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
    private final int SPACING = 20;
    private final int RECT_WIDTH = 13;
    private final int RECT_HEIGHT = 13;
    private final Set<Line> trailLines = new HashSet<>();
    Rectangle rect;

    public PlayerView(Player player) {

        super(new Pane());

        this.rect = new Rectangle(-RECT_WIDTH/2.d,-RECT_HEIGHT/2.d,RECT_WIDTH,RECT_HEIGHT);
        rect.setFill(Color.YELLOW);
        rect.setRotate(45);

        this.getChildren().add(rect);

        rect.setTranslateX(player.getPosition().x * SPACING);
        rect.setTranslateY(player.getPosition().y * SPACING);
    }

    public void update(Player model){
        rect.setTranslateX(model.getPosition().x * SPACING);
        rect.setTranslateY(model.getPosition().y * SPACING);

        List<Point> trail = model.getTrail();

        this.getChildren().removeAll(trailLines);

        trailLines.clear();

        if(trail.size() < 2) return;

        for (int i = 1; i < trail.size(); i++) {
            Point pointA = trail.get(i-1);
            Point pointB = trail.get(i);

            Line line = new Line(pointA.x * SPACING, pointA.y * SPACING, pointB.x * SPACING, pointB.y * SPACING);

            trailLines.add(line);

            this.getChildren().add(line);
        }
    }
}
