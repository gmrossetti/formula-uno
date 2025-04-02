package com.gmrossetti.mdp.view;

import com.gmrossetti.mdp.circuit.tile.ITile;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GridPointView extends Circle {
    public GridPointView(ITile tile){
        super(0, 0, 3, getColorFormGridPointType(tile));
    }

    private static Color getColorFormGridPointType(ITile tile){
        return tile.isOnTrack() ? Color.web("#ADD8E6") : Color.web("#FF4500");
    }
}
