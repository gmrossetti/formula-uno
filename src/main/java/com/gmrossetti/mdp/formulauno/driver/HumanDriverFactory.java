package com.gmrossetti.mdp.formulauno.driver;

import com.gmrossetti.mdp.formulauno.circuit.ICircuit;
import com.gmrossetti.mdp.formulauno.core.InputHandler;
import com.gmrossetti.mdp.formulauno.pawn.IPawn;
import com.gmrossetti.mdp.formulauno.pawn.PawnFactory;

/**
 * Factory class to create a HumanDriver instance.
 */
public class HumanDriverFactory {
    public static IDriver build(ICircuit circuit){
        IPawn car = PawnFactory.buildPawn(circuit.getRaceStartPoint().getGridPoint());

        return new HumanDriver(car, InputHandler.getInstance(), circuit);
    }
}
