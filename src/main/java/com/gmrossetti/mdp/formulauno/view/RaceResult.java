package com.gmrossetti.mdp.formulauno.view;

import com.gmrossetti.mdp.formulauno.core.GameState;
import com.gmrossetti.mdp.formulauno.leaderboard.Leaderboard;
import com.gmrossetti.mdp.formulauno.leaderboard.LeaderboardEntry;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.StageStyle;

/**
 * RaceResult is a custom JavaFX dialog that displays the race results
 * for the human driver, including their position or disqualification status.
 */
public class RaceResult extends Dialog<Void> {
    /**
     * Constructor for RaceResult.
     * @param gameState The GameState object that contains the current state of the game.
     */
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

    /**
     * Gets the title for the driver based on their status.
     * @param humanCarDriverLeaderboardEntry The leaderboard entry for the human driver.
     * @return The title string.
     */
    private String getTitleForDriver(LeaderboardEntry humanCarDriverLeaderboardEntry) {
        if (humanCarDriverLeaderboardEntry.isDisqualified()) {
            return "Squalificato!";
        } else {
            return "Complimenti!";
        }
    }

    /**
     * Gets the message for the driver based on their status and position.
     * @param gameState The GameState object that contains the current state of the game.
     * @param humanCarDriverLeaderboardEntry The leaderboard entry for the human driver.
     * @param leaderBoard The leaderboard object.
     * @return The message string.
     */
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

    /**
     * Configures the dialog with the title and message.
     * @param title The title of the dialog.
     * @param message The message to be displayed in the dialog.
     */
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