package com.gmrossetti.mdp.formulauno.driver;

import com.gmrossetti.mdp.formulauno.circuit.ICircuit;
import com.gmrossetti.mdp.formulauno.core.InputHandler;
import com.gmrossetti.mdp.formulauno.pawn.IPawn;
import com.gmrossetti.mdp.formulauno.pawn.PawnFactory;

public class HumanDriverFactory {
    private static final int MAX_HUMAN_INSTANCE_COUNT = 1;
    private static int instanceCount = 0;

    public static IDriver build(ICircuit circuit){
//        if(instanceCount >= MAX_HUMAN_INSTANCE_COUNT)
//            throw new IllegalStateException("Cannot build more than " + MAX_HUMAN_INSTANCE_COUNT + " HumanDriver instances.");

        IPawn car = PawnFactory.buildPawn(circuit.getRaceStartPoint().getGridPoint());
        HumanDriver humanDriver = new HumanDriver(car, InputHandler.getInstance(), circuit);

        instanceCount++;

        return humanDriver;
    }
}
