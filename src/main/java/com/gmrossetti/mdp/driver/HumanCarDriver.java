package com.gmrossetti.mdp.driver;

import com.gmrossetti.mdp.actor.IPawn;
import com.gmrossetti.mdp.circuit.ICircuit;
import com.gmrossetti.mdp.entity.cartesian.GridLine;

public class HumanCarDriver extends CarDriver {
    HumanCarDriver(IPawn car, ICircuit circuit) {
        super(car, circuit.getWaypointsHead());
    }

    public final GridLine makeMove(Move move){
        return super.processMove(move);
    }
}
