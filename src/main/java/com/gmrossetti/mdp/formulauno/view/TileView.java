package com.gmrossetti.mdp.formulauno.view;

import com.gmrossetti.mdp.formulauno.circuit.tile.ITile;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * TileView is a visual representation of a tile in the game.
 * It extends Circle to represent the tile as a circle on the screen.
 * The color of the circle is determined by the type of tile (on track or not).
 */
public class TileView extends Circle {
    /**
     * Constructor for TileView.
     * @param tile The ITile object that this TileView represents.
     */
    public TileView(ITile tile){
        super(0, 0, 3, getColorFromTileType(tile));
    }

    /**
     * Constructor for TileView with specified radius.
     * @param tile The ITile object that this TileView represents.
     */
    private static Color getColorFromTileType(ITile tile){
        return tile.isOnTrack() ? Color.web("#ADD8E6") : Color.web("#FF4500");
    }
}
