package com.gmrossetti.mdp.pawn;

import com.gmrossetti.mdp.cartesian.GridPoint;

// TODO: add type and support for ipotetical different pawns
public final class PawnFactory {
    public static IPawn buildPawn(GridPoint initialPosition){
        return new Car(initialPosition);
    }
}
