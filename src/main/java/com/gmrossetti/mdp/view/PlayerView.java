package com.gmrossetti.mdp.view;

import com.gmrossetti.mdp.model.Point;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PlayerView {
    private final Pane view;

    public PlayerView() {
        this.view = new Pane();

        Rectangle rect = new Rectangle(-13 /2.d, -13 /2.d,13.d, 13.d);
        rect.setFill(Color.YELLOW);
        rect.setRotate(45);

        this.view.getChildren().add(rect);
    }

    public Node getView(Point position){
        int spacing = 20;

        this.view.setTranslateX(position.y * spacing);
        this.view.setTranslateY(position.x * spacing);

        return this.view;
    }
}
