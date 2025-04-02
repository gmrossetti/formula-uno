package com.gmrossetti.mdp.driver;

import com.gmrossetti.mdp.pawn.IPawn;
import com.gmrossetti.mdp.circuit.ICircuit;
import com.gmrossetti.mdp.pawn.PawnFactory;

public class HumanCarDriverFactory {
    private static final int MAX_INSTANCE_COUNT = 1;
    private static int instanceCount = 0;
    public static HumanCarDriver build(ICircuit circuit){
        if(instanceCount >= MAX_INSTANCE_COUNT)
            throw new IllegalStateException("Cannot build more than " + MAX_INSTANCE_COUNT + " HumanCarDriver instances.");

        IPawn car = PawnFactory.buildPawn(circuit.getRaceStartPoint());
        HumanCarDriver humanCarDriver = new HumanCarDriver(car, circuit);

        instanceCount++;

        return humanCarDriver;
    }
}
