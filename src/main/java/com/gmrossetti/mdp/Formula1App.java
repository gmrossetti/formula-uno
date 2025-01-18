package com.gmrossetti.mdp;

import com.gmrossetti.mdp.controller.CircuitController;
import com.gmrossetti.mdp.model.Circuit;
import com.gmrossetti.mdp.view.CircuitView;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Formula1App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        StackPane stackPane = new StackPane();
        stackPane.setStyle("-fx-padding: 20px;");

        Pane rootPane = new Pane();
//        rootPane.setPrefSize(1280, 720);
//        rootPane.setPrefSize(1920, 1080);
        rootPane.setPrefSize(1920, 1000);
        rootPane.setStyle("-fx-background-color: #ccc; -fx-padding: 20px;");

        CircuitController circuitCtrl = new CircuitController();

        stackPane.getChildren().addAll(rootPane,circuitCtrl.getView());

        // Crea la scena e imposta il StackPane come radice
        Scene scene = new Scene(stackPane);

        // Imposta il titolo della finestra
        primaryStage.setTitle("Formula 1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static Parent generateGridView() {
        Pane root = new Pane();
        root.setPrefSize(600, 600);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                onUpdate();
            }
        };
        timer.start();

        return root;
    }
    private static Pane printGridPoints(){
/*        Color gridPointColor = new Color(0,255,255,1);

        for (int y = 0;  y < 720 / 10;  y++) {
            for (int x = 0; x < 1280 / 10; x++) {
                boolean isValid = Math.round(Math.random()) == 1;
                gridPoints.add(new Point(x * 10, y * 10, isValid));

            }
        }*/
        
        Canvas canvas = new Canvas(1280,720);
        GraphicsContext g = canvas.getGraphicsContext2D();

        Pane pane = new Pane(canvas);
        pane.setPrefSize(1280,720);
//        pane.insetsProperty(10);

        g.clearRect(0, 0, 1280, 720);

        Circuit circuit = new Circuit();

        /*gridPoints.forEach(p -> {
            g.setFill(Color.RED);

            g.fillOval(p.x - 1, p.y - 1, 2, 2);
        });
*/
//        for (int x = 0; x < circuit.grid.length; x++) {
//            for (int y = 0; y < circuit.grid[0].length; y++) {
//
//                Point p = circuit.grid[x][y];
//
//                if(p == null){
//                    g.setFill(Color.GREEN);
//                } else {
//                    g.setFill(Color.RED);
//                }
//
//                int thickness = 5;
//                int gap = 30;
//
//                g.fillOval(gap * y - thickness / 2, gap * x - thickness / 2, thickness, thickness);
//            }
//        }

        return pane;
    }

    private static void onUpdate() {

    }
}
