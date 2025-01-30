package com.gmrossetti.mdp.controller;

import com.gmrossetti.mdp.model.GridPoint;
import com.gmrossetti.mdp.model.Player;
import com.gmrossetti.mdp.view.PlayerView;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.Random;

public class PlayerController {
    private final Player model;
    private final PlayerView view;

    public PlayerController(GridPoint positionGridPoint) {
        this.model = new Player(positionGridPoint);
        this.view = new PlayerView(positionGridPoint);
    }

    public void nextMove(ArrayList<GridPoint> gridPointsInRange){
        ArrayList<GridPoint> usableGridPoints = new ArrayList<>();

        for (GridPoint gridPoint:
                gridPointsInRange) {
            if(gridPoint.type == GridPoint.GridPointType.INSIDE && !gridPoint.isOccupied()) {
                usableGridPoints.add(gridPoint);
            }
        }

        int rnd = new Random().nextInt(usableGridPoints.size());

        this.model.makeMove(usableGridPoints.get(rnd));

        this.view.update(this.model);
    }


    public Player getModel(){
        return this.model;
    }
    public Node getView(){
        return this.view;
    }
}
