package com.gmrossetti.mdp.formulauno.view;

import com.gmrossetti.mdp.formulauno.core.GameState;
import com.gmrossetti.mdp.formulauno.leaderboard.Leaderboard;
import com.gmrossetti.mdp.formulauno.leaderboard.LeaderboardEntry;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.StageStyle;

public class RaceResult extends Dialog<Void> {
    public RaceResult(GameState gameState) {
        // Ottieni le informazioni necessarie
        Leaderboard leaderBoard = gameState.getLeaderboard();
        LeaderboardEntry humanCarDriverLeaderboardEntry = leaderBoard.getLeaderboardEntry(gameState.getHumanDriver());

        // Crea il messaggio e il titolo
        String title = getTitleForDriver(humanCarDriverLeaderboardEntry);
        String message = getMessageForDriver(gameState, humanCarDriverLeaderboardEntry, leaderBoard);

        // Imposta la finestra di dialogo
        configureDialog(title, message);
    }

    private String getTitleForDriver(LeaderboardEntry humanCarDriverLeaderboardEntry) {
        if (humanCarDriverLeaderboardEntry.isDisqualified()) {
            return "Squalificato!";
        } else {
            return "Complimenti!";
        }
    }

    private String getMessageForDriver(GameState gameState, LeaderboardEntry humanCarDriverLeaderboardEntry, Leaderboard leaderBoard) {
        StringBuilder message = new StringBuilder();

        if (humanCarDriverLeaderboardEntry.isDisqualified()) {
            message.append("Sei uscito dalla pista!");
        } else {
            message.append("Hai concluso la gara in ")
                    .append(leaderBoard.getPosition(gameState.getHumanDriver()))
                    .append("^ posizione!");
        }

        message.append("\nIl gioco è stato resettato.");
        return message.toString();
    }

    private void configureDialog(String title, String message) {
        // Imposta il titolo e il messaggio nella finestra di dialogo
        setTitle(title);
        setHeaderText(message);

        // Aggiungi il pulsante OK
        getDialogPane().getButtonTypes().add(ButtonType.OK);

        // Imposta lo stile della finestra di dialogo (senza bordi)
        initStyle(StageStyle.UNDECORATED);
    }
}