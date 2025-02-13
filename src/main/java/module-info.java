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
    exports com.gmrossetti.mdp.model;
    opens com.gmrossetti.mdp.model to javafx.fxml;
}