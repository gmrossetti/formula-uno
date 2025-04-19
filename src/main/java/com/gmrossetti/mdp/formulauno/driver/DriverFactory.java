package com.gmrossetti.mdp.formulauno.driver;

import com.gmrossetti.mdp.formulauno.circuit.ICircuit;
import com.gmrossetti.mdp.formulauno.core.InputHandler;
import com.gmrossetti.mdp.formulauno.pawn.IPawn;
import com.gmrossetti.mdp.formulauno.pawn.PawnFactory;
import com.gmrossetti.mdp.formulauno.strategy.IStrategy;
import com.gmrossetti.mdp.formulauno.strategy.StrategyFactory;
import com.gmrossetti.mdp.formulauno.strategy.StrategyParameters;
import com.gmrossetti.mdp.formulauno.strategy.StrategyType;

/**
 * Factory class for creating driver instances.
 * It provides methods to create both human and bot drivers.
 */
public class DriverFactory {
    /**
     * Creates a human driver for the given circuit.
     *
     * @param circuit the circuit for which the driver is created
     * @return a new instance of HumanDriver
     */
    public static IDriver buildHumanDriver(ICircuit circuit){
        IPawn car = PawnFactory.buildPawn(circuit.getRaceStartPoint().getGridPoint());

        return new HumanDriver(car, InputHandler.getInstance(), circuit);
    }

    /**
     * Creates a bot driver for the given circuit with the specified strategy type and parameters.
     *
     * @param circuit the circuit for which the driver is created
     * @param strategyType the type of strategy to be used by the bot driver
     * @param strategyParameters the parameters for the strategy
     * @return a new instance of BotDriver
     */
    public static IDriver buildBotDriver(ICircuit circuit, StrategyType strategyType, StrategyParameters strategyParameters){
        IPawn botCar = PawnFactory.buildPawn(circuit.getRaceStartPoint().getGridPoint());
        IStrategy strategy = StrategyFactory.buildStrategy(strategyType, strategyParameters);

        return new BotDriver(botCar, circuit, strategy);
    }
}
