package com.gmrossetti.mdp.actor;

import com.gmrossetti.mdp.level.LevelParser;
import com.gmrossetti.mdp.entity.CircuitGridPoint;
import com.gmrossetti.mdp.entity.GridPoint;

import java.io.IOException;
import java.util.*;

public class Circuit {
    private final CircuitGridPoint[][] grid;
    private final List<CircuitGridPoint> raceStartLine;
    private final CircuitGridPoint raceStartCircuitGridPoint;

    public Circuit(){
        try {
            this.grid = LevelParser.parseImageToGrid("circuit1");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.raceStartLine = new ArrayList<>();
        initRaceStartEndLines();

        raceStartCircuitGridPoint = raceStartLine.get(raceStartLine.size() / 2);
    }

    public CircuitGridPoint getGridPoint(int x, int y) {
        try {
            CircuitGridPoint circuitGridPoint = this.grid[y][x];
            return new CircuitGridPoint(circuitGridPoint);
        } catch (ArrayIndexOutOfBoundsException e) {
            return new CircuitGridPoint(x, y, CircuitGridPoint.GridPointType.OUTSIDE);
        }
    }

    public CircuitGridPoint getGridPoint(GridPoint gp){
        return this.getGridPoint(gp.x,gp.y);
    }

    public CircuitGridPoint getRaceStartPoint(){
        return this.raceStartCircuitGridPoint;
    }

    public int getGridWidth(){
        return this.grid[0].length;
    }

    public int getGridHeight(){
        return this.grid.length;
    }

    private void initRaceStartEndLines() {
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                CircuitGridPoint circuitGridPoint = grid[y][x];

                if (circuitGridPoint.type == CircuitGridPoint.GridPointType.START) {
                    this.raceStartLine.add(circuitGridPoint);
                }
            }
        }

        if(raceStartLine.isEmpty()){
            throw new RuntimeException("Race start line not provided.");
        }
    }

    /*public boolean isValidRoute(GridLine routeLine){



//        routeLine.getGridIntersections().forEach(intesectionPoint -> mIrc.join(mSession, join));

    }*/


    /*public boolean isValidRoute(Set<GridPoint> routePoints){
        for (GridPoint routePoint:
                routePoints) {
            CircuitGridPoint routeCircuitGridPoint = this.getGridPoint(routePoint);

            if(routeCircuitGridPoint.type == CircuitGridPoint.GridPointType.OUTSIDE){
                return false;
            }
        }

        return true;
    }*/

    /*public Set<CircuitGridPoint> pointsToGridPoints(Set<Point> points){
        Set<CircuitGridPoint> circuitGridPoints = new HashSet<>();

        for (Point point:
                points) {
            circuitGridPoints.add(this.getGridPoint(point));
        }

        return circuitGridPoints;
    }*/

    /*public List<CircuitGridPoint> getGridRoute(Point p1, Point p2){
        Set<Pair<Double, Double>>intersectionPoints = Point.getSegmentGridIntersections(p1,p2);

        Pair<Double,Double> point1 = new Pair<>((double)p1.x,(double)p1.y);

        intersectionPoints.stream().sorted(new Comparator<Pair<Double, Double>>() {
            @Override
            public int compare(Pair<Double, Double> o1, Pair<Double, Double> o2) {
                if(Point.getDistance(point1,o1) > Point.getDistance(point1,o2)){

                }
                if(Point.getDistance(point1,o1) > Point.getDistance(point1,o2)){

                }
            }
        })
    }*/
}