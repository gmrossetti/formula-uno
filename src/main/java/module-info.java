module com.gmrossetti.mdp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.gmrossetti.mdp to javafx.fxml;
    exports com.gmrossetti.mdp;
    exports com.gmrossetti.mdp.controller;
    opens com.gmrossetti.mdp.controller to javafx.fxml;
}