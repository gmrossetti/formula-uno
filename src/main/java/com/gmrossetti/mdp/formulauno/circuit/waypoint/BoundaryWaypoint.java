package com.gmrossetti.mdp.formulauno.circuit.waypoint;

import com.gmrossetti.mdp.formulauno.cartesian.GridPoint;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a boundary waypoint in the circuit.
 * A boundary waypoint is defined by its center point, width, height, and type (START or FINISH).
 * It also calculates the grid points within its range based on its dimensions.
 */
public class BoundaryWaypoint extends Waypoint {
    /**
     * The type of the boundary waypoint, either START or FINISH.
     */
    private final Type type;

    /**
     * The width of the boundary waypoint.
     */
    public int getWidth() {
        return width;
    }

    /**
     * The height of the boundary waypoint.
     */
    public int getHeight() {
        return height;
    }

    private final int width;
    private final int height;

    public Type getType() {
        return type;
    }

    public enum Type {
        START,
        FINISH
    }

    /**
     * Constructor for creating a BoundaryWaypoint.
     *
     * @param center The center point of the boundary waypoint.
     * @param width  The width of the boundary waypoint.
     * @param height The height of the boundary waypoint.
     * @param type   The type of the boundary waypoint (START or FINISH).
     */
    public BoundaryWaypoint(GridPoint center, int width, int height, Type type){
        super(center);

        this.width = width;
        this.height = height;
        this.type = type;

        initWithinRangeGridPoints();
    }
    @Override
    void initWithinRangeGridPoints() {
        final double x0 = this.center.x - (width / 2.f);
        final double x1 = this.center.x + (width / 2.f);

        final double y0 = this.center.y - (height / 2.f);
        final double y1 = this.center.y + (height / 2.f);

        final double minX = Math.min(x0, x1);
        final double maxX = Math.max(x0, x1);
        final double minY = Math.min(y0, y1);
        final double maxY = Math.max(y0, y1);

        final Set<GridPoint> withinRangeGPs = new HashSet<>();

        for (int x = (int) Math.ceil(minX); x <= (int) Math.floor(maxX); x++) {
            for (int y = (int) Math.ceil(minY); y <= (int) Math.floor(maxY); y++) {
                withinRangeGPs.add(new GridPoint(x, y));
            }
        }

        this.withinRangeGPs = withinRangeGPs;
    }
}
