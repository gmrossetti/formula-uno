module com.gmrossetti.mdp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.gmrossetti.mdp to javafx.fxml;
    exports com.gmrossetti.mdp;
    exports com.gmrossetti.mdp.actor;
    opens com.gmrossetti.mdp.actor to javafx.fxml;
    exports com.gmrossetti.mdp.level;
    opens com.gmrossetti.mdp.level to javafx.fxml;
    exports com.gmrossetti.mdp.entity.cartesian;
    opens com.gmrossetti.mdp.entity.cartesian to javafx.fxml;
    exports com.gmrossetti.mdp.entity.waypoint;
    opens com.gmrossetti.mdp.entity.waypoint to javafx.fxml;
}