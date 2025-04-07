package com.gmrossetti.mdp.driver;

import com.gmrossetti.mdp.circuit.ICircuit;
import com.gmrossetti.mdp.pawn.IPawn;
import com.gmrossetti.mdp.pawn.PawnFactory;
import com.gmrossetti.mdp.strategy.IStrategy;
import com.gmrossetti.mdp.strategy.StrategyFactory;
import com.gmrossetti.mdp.strategy.StrategyParameters;
import com.gmrossetti.mdp.strategy.StrategyType;

public class BotDriverFactory {
    public static IDriver build(ICircuit circuit, StrategyType strategyType, StrategyParameters strategyParameters){
        IPawn botCar = PawnFactory.buildPawn(circuit.getRaceStartPoint().getGridPoint());
        IStrategy strategy = StrategyFactory.buildStrategy(strategyType, strategyParameters);

        return new BotDriver(botCar, circuit, strategy);
    }
}
