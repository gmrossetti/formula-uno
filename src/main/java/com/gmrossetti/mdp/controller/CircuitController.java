package com.gmrossetti.mdp.controller;

import com.gmrossetti.mdp.model.Circuit;
import com.gmrossetti.mdp.model.Point;
import com.gmrossetti.mdp.view.CircuitView;
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

                Point point2add = this.model.getGridPoint(x,y);

//                if(point2add == null) point2add = new Point(x,y);

                this.view.addPoint(point2add);
            }
        }
    }

    public Point getRaceStartPoint(){
        return this.model.getRaceStartPoint();
    }

    public Pane getView() {
        return view.getView();
    }
}
