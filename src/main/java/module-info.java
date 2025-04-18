module com.gmrossetti.mdp.formulauno {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.json;

    opens com.gmrossetti.mdp.formulauno to javafx.fxml;
    exports com.gmrossetti.mdp.formulauno;
    exports com.gmrossetti.mdp.formulauno.pawn;
    opens com.gmrossetti.mdp.formulauno.pawn to javafx.fxml;
    exports com.gmrossetti.mdp.formulauno.parser;
    opens com.gmrossetti.mdp.formulauno.parser to javafx.fxml;
    exports com.gmrossetti.mdp.formulauno.cartesian;
    opens com.gmrossetti.mdp.formulauno.cartesian to javafx.fxml;
    exports com.gmrossetti.mdp.formulauno.circuit.waypoint;
    opens com.gmrossetti.mdp.formulauno.circuit.waypoint to javafx.fxml;
    exports com.gmrossetti.mdp.formulauno.circuit;
    opens com.gmrossetti.mdp.formulauno.circuit to javafx.fxml;
    exports com.gmrossetti.mdp.formulauno.circuit.tile;
    opens com.gmrossetti.mdp.formulauno.circuit.tile to javafx.fxml;
}