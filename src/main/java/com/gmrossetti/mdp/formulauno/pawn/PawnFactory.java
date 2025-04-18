package com.gmrossetti.mdp.formulauno.pawn;

import com.gmrossetti.mdp.formulauno.cartesian.GridPoint;

// TODO: add type and support for ipotetical different pawns
/**
 * Factory class for creating instances of IPawn.
 * Currently, it only creates instances of Car.
 */
public final class PawnFactory {
    public static IPawn buildPawn(GridPoint initialPosition){
        return new Car(initialPosition);
    }
}
