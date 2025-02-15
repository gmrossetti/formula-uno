package com.gmrossetti.mdp.driver;

import com.gmrossetti.mdp.actor.Car;
import com.gmrossetti.mdp.model.GridLine;
import com.gmrossetti.mdp.model.GridPoint;
import com.gmrossetti.mdp.model.Point;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

abstract public class CarDriver {
    public Car getCar() {
        return car;
    }

    private final Car car;

    public enum Move {
        TL, TM, TR, CL, CM, CR, BL, BM, BR
    }

    public CarDriver(Car car){
        this.car = car;
    }

    public final GridLine makeMove(CarDriver.Move move){
        GridPoint point2reach = getMovesPoints().get(move);

        final GridLine trace = new GridLine(this.car.getPosition(),point2reach);

        this.car.move(point2reach);

        return trace;
    }
    
    /*public Set<Point> getPointsInTrajectory(Point point2reach){
        Set<Pair<Double,Double>> intersectionCoords = Point.calculateIntersectionCoords(this.position, point2reach);

        Set<Point> pointsInTrajectory = new HashSet<>();

        for (Pair<Double,Double> coords:
                intersectionCoords) {
            pointsInTrajectory.addAll(Point.findClosestIntegerPoints(coords));
        }

        return pointsInTrajectory;
    }*/
    
    public final Map<CarDriver.Move,GridPoint> getMovesPoints(){
        final Map<CarDriver.Move,GridPoint> movesPoint = new HashMap<>();

        GridPoint pivot = this.car.getPivot();

        movesPoint.put(CarDriver.Move.TL, new GridPoint(pivot.x - 1, pivot.y - 1));
        movesPoint.put(CarDriver.Move.TM, new GridPoint(pivot.x, pivot.y - 1));
        movesPoint.put(CarDriver.Move.TR, new GridPoint(pivot.x + 1, pivot.y - 1));

        movesPoint.put(CarDriver.Move.CL, new GridPoint(pivot.x - 1, pivot.y));
        movesPoint.put(CarDriver.Move.CM, new GridPoint(pivot.x, pivot.y));
        movesPoint.put(CarDriver.Move.CR, new GridPoint(pivot.x + 1, pivot.y));

        movesPoint.put(CarDriver.Move.BL, new GridPoint(pivot.x - 1, pivot.y + 1));
        movesPoint.put(CarDriver.Move.BM, new GridPoint(pivot.x, pivot.y + 1));
        movesPoint.put(CarDriver.Move.BR, new GridPoint(pivot.x + 1, pivot.y + 1));

        return movesPoint;
    }
}
