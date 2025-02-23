package com.gmrossetti.mdp.driver;

import com.gmrossetti.mdp.actor.Car;
import com.gmrossetti.mdp.core.DriverMoveValidator;
import com.gmrossetti.mdp.entity.cartesian.GridLine;
import com.gmrossetti.mdp.entity.cartesian.GridPoint;
import com.gmrossetti.mdp.entity.cartesian.Point;
import com.gmrossetti.mdp.entity.waypoint.Waypoint;

import java.util.*;

public class BotStrategy {
    private final BotCarDriver carDriver;

    enum SpeedAction {
        BRAKE,
        NEUTRAL,
        GAS
    }
    private final double minVelocity;
    private final double deviationThreshold;
    private final double brakeDistance;
    private final double accelerateDistance;


    public BotStrategy(BotCarDriver carDriver, StrategyParameters strategyParameters){
        this.carDriver = carDriver;

        minVelocity = StrategyParametersScaler.getMinVelocity(strategyParameters);
        deviationThreshold = StrategyParametersScaler.getDeviationThreshold(strategyParameters);
        brakeDistance = StrategyParametersScaler.getBrakeDistance(strategyParameters);
        accelerateDistance = StrategyParametersScaler.getAccelerateDistance(strategyParameters);
    }

    public CarDriver.Move chooseBestMove(){
        final Map<CarDriver.Move, GridPoint> movesPoints = carDriver.getMovesPoints();

        final GridPoint currentPos = carDriver.getCar().getPosition();

        final Waypoint currentWaypoint = carDriver.waypointTarget;

        if(!carDriver.waypointTarget.hasPrevious()) throw new RuntimeException("CarDriver must be initialized with the second waypoint");

        final Waypoint previousWaypoint = carDriver.waypointTarget.getPrevious();

        final GridLine waypoint2waypoint = new GridLine(previousWaypoint.getCenter(), currentWaypoint.getCenter());
        final Point medianPoint = waypoint2waypoint.getMedianPoint();

        ArrayList<MoveCandidate> moveCandidates = new ArrayList<>();

        for (Map.Entry<CarDriver.Move,GridPoint> moveGridPoint:
            movesPoints.entrySet()) {

            GridPoint gp = moveGridPoint.getValue();

            double distanceToCurrentPos = gp.distanceTo(currentPos);
            double distanceToTarget = gp.distanceTo(currentWaypoint.getCenter());
            double distanceToMedian = gp.distanceTo(medianPoint);

            MoveCandidate moveCandidate = new MoveCandidate(moveGridPoint.getKey(), gp, distanceToCurrentPos, distanceToTarget, distanceToMedian);

            moveCandidates.add(moveCandidate);
        }

        moveCandidates.sort(Comparator.comparingDouble(MoveCandidate::getDistanceToCurrent));

        List<MoveCandidate> brakeCandidates = moveCandidates.subList(0,3);
        List<MoveCandidate> neutralCandidates = moveCandidates.subList(3,6);
        List<MoveCandidate> gasCandidates = moveCandidates.subList(6,9);

        List<MoveCandidate> brakeCandidatesFiltered = filterOutUnusableGP(brakeCandidates);
        List<MoveCandidate> neutralCandidatesFiltered = filterOutUnusableGP(neutralCandidates);
        List<MoveCandidate> gasCandidatesFiltered = filterOutUnusableGP(gasCandidates);

        SpeedAction speedAction = findSpeedAction();

        MoveCandidate bestMoveCandidate = selectBestMove(speedAction, brakeCandidatesFiltered, neutralCandidatesFiltered, gasCandidatesFiltered);

        return (bestMoveCandidate == null) ? CarDriver.Move.BL : bestMoveCandidate.getMove();
    }

    private SpeedAction findSpeedAction(){
        Car car = carDriver.getCar();

        final Point median = getMedian();

        GridPoint pivot = car.getPivot();
        GridPoint target = carDriver.waypointTarget.getCenter();

        GridLine currentPosToTarget = new GridLine(car.getPosition(), target);
        GridLine currentPosToPivot = new GridLine(car.getPosition(), pivot);

        double trajectoryDeviationDegrees = 0;

        if(!currentPosToTarget.isDegenerate() && !currentPosToPivot.isDegenerate()){
            double currentPosToTargetDegrees = currentPosToTarget.getSlopeCoefficientToDegrees();
            double currentPosToPivotDegrees = currentPosToPivot.getSlopeCoefficientToDegrees();

            trajectoryDeviationDegrees = Math.abs(currentPosToTargetDegrees - currentPosToPivotDegrees);
        }

        if((pivot.distanceTo(target) < median.distanceTo(target) - brakeDistance ||
                trajectoryDeviationDegrees > deviationThreshold) &&
                car.getVelocityModule() > minVelocity){
            return SpeedAction.BRAKE;
        }

        if(pivot.distanceTo(target) > median.distanceTo(target) + accelerateDistance && car.getVelocityModule() < 2){
            return SpeedAction.GAS;
        }

        return SpeedAction.NEUTRAL;
    }

    private MoveCandidate selectBestMove(SpeedAction speedAction, List<MoveCandidate> brakes,
                                         List<MoveCandidate> neutrals, List<MoveCandidate> gas){

        brakes.sort(Comparator.comparingDouble(MoveCandidate::getDistanceToTarget));
        neutrals.sort(Comparator.comparingDouble(MoveCandidate::getDistanceToTarget));
        gas.sort(Comparator.comparingDouble(MoveCandidate::getDistanceToTarget));

        List<List<MoveCandidate>> prioritizedMoves;

        switch (speedAction) {
            case BRAKE:
                prioritizedMoves = Arrays.asList(brakes, neutrals, gas);
                break;
            case NEUTRAL:
                prioritizedMoves = Arrays.asList(neutrals, brakes, gas);
                break;
            case GAS:
                prioritizedMoves = Arrays.asList(gas, neutrals, brakes);
                break;
            default:
                return null;
        }

        for (List<MoveCandidate> moveList : prioritizedMoves) {
            if (!moveList.isEmpty()) {
                return moveList.get(0);
            }
        }

        return null;
    }

    private Point getMedian(){
        final Waypoint currentWaypoint = carDriver.waypointTarget;
        final Waypoint previousWaypoint = carDriver.waypointTarget.getPrevious();

        final GridLine waypoint2waypoint = new GridLine(previousWaypoint.getCenter(), currentWaypoint.getCenter());

        return waypoint2waypoint.getMedianPoint();
    }

    private List<MoveCandidate> filterOutUnusableGP(List<MoveCandidate> moveCandidatesToFilter){
        List<MoveCandidate> moveCandidatesModified = new ArrayList<>(moveCandidatesToFilter);

        Iterator<MoveCandidate> it = moveCandidatesModified.iterator();
        while (it.hasNext()) {
            MoveCandidate moveCandidate = it.next();

            if(carDriver.getCar().isStationary() && carDriver.getCar().getPosition().equals(moveCandidate.getMovePoint())){
                it.remove();
                continue;
            }

            final boolean isIpoteticMoveValid = DriverMoveValidator.isMoveValid(new GridLine(carDriver.getCar().getPosition(), moveCandidate.getMovePoint()), carDriver.getCircuit());

            if(!isIpoteticMoveValid){
                it.remove();
            }
        }

        return moveCandidatesModified;
    }
}
