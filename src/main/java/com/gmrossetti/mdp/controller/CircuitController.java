package com.gmrossetti.mdp.controller;

import com.gmrossetti.mdp.model.Circuit;
import com.gmrossetti.mdp.model.Point;
import com.gmrossetti.mdp.view.CircuitView;
import com.gmrossetti.mdp.view.PointView;
import javafx.scene.layout.Pane;

public class CircuitController {

    private Circuit model;
    private CircuitView view;

    public CircuitController(){
        this.model = new Circuit();
        this.view = new CircuitView();

        this.initView();
    }

//    public CircuitController(Circuit circuit){
//        this.model = circuit;
//        this.view = new CircuitView(model);
//    }

    public void initView(){
        for (int x = 0; x < this.model.getGridWidth(); x++) {
            for (int y = 0; y < this.model.getGridHeight(); y++){
                /*PointView pointView = new PointView(this.model.getGridPoint(x,y));

                pointView.getView().setTranslateX(this.spacing * y);
                pointView.getView().setTranslateY(this.spacing * x);

                pane.getChildren().add(pointView.getView());*/

                Point point2add = this.model.getGridPoint(x,y);

                if(point2add == null) point2add = new Point(x,y);

                this.view.addPoint(point2add);
            }
        }
    }

    public Pane getView() {
        return view.getView();
    }
}
