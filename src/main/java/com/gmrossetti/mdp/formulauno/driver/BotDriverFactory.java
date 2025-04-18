package com.gmrossetti.mdp.formulauno.driver;

import com.gmrossetti.mdp.formulauno.circuit.ICircuit;
import com.gmrossetti.mdp.formulauno.pawn.IPawn;
import com.gmrossetti.mdp.formulauno.pawn.PawnFactory;
import com.gmrossetti.mdp.formulauno.strategy.IStrategy;
import com.gmrossetti.mdp.formulauno.strategy.StrategyFactory;
import com.gmrossetti.mdp.formulauno.strategy.StrategyParameters;
import com.gmrossetti.mdp.formulauno.strategy.StrategyType;

public class BotDriverFactory {
    public static IDriver build(ICircuit circuit, StrategyType strategyType, StrategyParameters strategyParameters){
        IPawn botCar = PawnFactory.buildPawn(circuit.getRaceStartPoint().getGridPoint());
        IStrategy strategy = StrategyFactory.buildStrategy(strategyType, strategyParameters);

        return new BotDriver(botCar, circuit, strategy);
    }
}
