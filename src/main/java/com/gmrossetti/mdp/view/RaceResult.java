package com.gmrossetti.mdp.view;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.StageStyle;

public class RaceResult extends Dialog<Void> {
    public RaceResult(String title, String message) {
        setTitle(title);
        setHeaderText(message);
        getDialogPane().getButtonTypes().add(ButtonType.OK);
        initStyle(StageStyle.UNDECORATED);
    }
}
