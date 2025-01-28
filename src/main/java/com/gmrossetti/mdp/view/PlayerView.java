package com.gmrossetti.mdp.view;

import com.gmrossetti.mdp.model.Player;
import com.gmrossetti.mdp.model.Point;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PlayerView extends Pane {
//    private final Pane view;

    private final int spacing = 20;

    public PlayerView(Point position) {

        super(new Pane());

        Rectangle rect = new Rectangle(-13 /2.d, -13 /2.d,13.d, 13.d);
        rect.setFill(Color.YELLOW);
        rect.setRotate(45);

        this.getChildren().add(rect);

        this.setTranslateX(position.y * spacing);
        this.setTranslateY(position.x * spacing);
    }

    public void update(Player model){
        this.setTranslateX(model.getPosition().y * spacing);
        this.setTranslateY(model.getPosition().x * spacing);
    }
}
