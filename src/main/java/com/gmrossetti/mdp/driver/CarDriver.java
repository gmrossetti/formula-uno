package com.gmrossetti.mdp.driver;

import com.gmrossetti.mdp.actor.Car;
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

    public final Pair<Point,Point> makeMove(CarDriver.Move move){
        Point point2reach = getMovesPoints().get(move);

        final Pair<Point,Point> trace = new Pair<>(this.car.getPosition(),point2reach);

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
    
    public final Map<CarDriver.Move,Point> getMovesPoints(){
        final Map<CarDriver.Move,Point> movesPoint = new HashMap<>();

        Point pivot = this.car.getPivot();

        movesPoint.put(CarDriver.Move.TL, new Point(pivot.x - 1, pivot.y - 1));
        movesPoint.put(CarDriver.Move.TM, new Point(pivot.x, pivot.y - 1));
        movesPoint.put(CarDriver.Move.TR, new Point(pivot.x + 1, pivot.y - 1));

        movesPoint.put(CarDriver.Move.CL, new Point(pivot.x - 1, pivot.y));
        movesPoint.put(CarDriver.Move.CM, new Point(pivot.x, pivot.y));
        movesPoint.put(CarDriver.Move.CR, new Point(pivot.x + 1, pivot.y));

        movesPoint.put(CarDriver.Move.BL, new Point(pivot.x - 1, pivot.y + 1));
        movesPoint.put(CarDriver.Move.BM, new Point(pivot.x, pivot.y + 1));
        movesPoint.put(CarDriver.Move.BR, new Point(pivot.x + 1, pivot.y + 1));

        return movesPoint;
    }
}
