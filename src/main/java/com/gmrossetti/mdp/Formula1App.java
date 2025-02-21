package com.gmrossetti.mdp;

import com.gmrossetti.mdp.core.GameManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class Formula1App extends Application {
    private final GameManager gameManager = GameManager.getInstance();

    @Override
    public void start(Stage primaryStage) {
        final Scene gameScene = gameManager.getGameScene();

        gameScene.getStylesheets().add(getClass().getResource("debug.css").toExternalForm());

        // Imposta il titolo della finestra
        primaryStage.setTitle("Formula 1");
        primaryStage.setScene(gameScene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    /*
    public static void printSceneTree(Scene scene) {
        if (scene != null && scene.getRoot() != null) {
            System.out.println("=== Scene Tree ===");
            printNodeTree(scene.getRoot(), "");
        } else {
            System.out.println("Scene is null or has no root.");
        }
    }

    public static void printNodeTree(Node node, String prefix) {
        // Stampa il nodo attuale con il prefisso (indentazione)
        System.out.println(prefix + node.getClass().getSimpleName() + " [" + node + "]");

        // Se il nodo è un contenitore (Parent), esplora i figli
        if (node instanceof Parent) {
            for (Node child : ((Parent) node).getChildrenUnmodifiable()) {
                printNodeTree(child, prefix + "  ");  // Ricorsione con indentazione
            }
        }
    }*/
}
