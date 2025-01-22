package com.gmrossetti.mdp.view;

import com.gmrossetti.mdp.model.Point;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class PlayerView {

    private final Pane view;

    private final int spacing = 20;

    public PlayerView() {
        this.view = new Pane();

        this.view.getChildren().add(new Rectangle(10, 10, Color.VIOLET));
    }

    public Node getView(Point position){
        this.view.setTranslateX(position.y * spacing);
        this.view.setTranslateY(position.x * spacing);

//        this.view.setTranslateX(10 * spacing);
//        this.view.setTranslateY(10 * spacing);

        return this.view;
    }
}
