package com.example.tictaktoe;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TicTakToe extends Application {

    private Button buttons[][] = new Button[3][3];
    private Label playerXScoreLabel, playerOScoreLabel;

    private int playerXScore = 0;
    private int playerOScore = 0;
    private boolean playerXTurn = true;

    private BorderPane createContent() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));
        // Title
        Label titleLabel = new Label("Tic Tac Toe");
        titleLabel.setStyle("-fx-font-size: 34pt; -fx-font-weight: bold;");
        root.setTop(titleLabel);
        BorderPane.setAlignment(titleLabel, Pos.CENTER);

        // Game Board
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = new Button("");
                button.setPrefSize(100, 100);
                button.setStyle("-fx-font-size: 24pt; -fx-font-weight: bold;");
                button.setOnAction(event -> buttonClicked(button));
                buttons[i][j] = button;
                gridPane.add(button, j, i);
            }
        }

        root.setCenter(gridPane);
        BorderPane.setAlignment(gridPane, Pos.CENTER);

        // Score
        HBox scoreBoard = new HBox(20);
        scoreBoard.setAlignment(Pos.CENTER);
        playerXScoreLabel = new Label("Player X: 0");
        playerXScoreLabel.setStyle("-fx-font-size: 16pt; -fx-font-weight: bold;");
        playerOScoreLabel = new Label("Player O: 0");
        playerOScoreLabel.setStyle("-fx-font-size: 16pt; -fx-font-weight: bold;");
        scoreBoard.getChildren().addAll(playerXScoreLabel, playerOScoreLabel);

        root.setBottom(scoreBoard);

        return root;
    }

    private void buttonClicked(Button button) {
        if (button.getText().equals("")) {
            button.setText(playerXTurn ? "X" : "O");
            playerXTurn = !playerXTurn;
            checkWinner(); // Call winner check after every move
        }
    }

    private void checkWinner() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (!buttons[i][0].getText().equals("") &&
                    buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                    buttons[i][1].getText().equals(buttons[i][2].getText())) {
                showWinner(buttons[i][0].getText());
                return;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (!buttons[0][i].getText().equals("") &&
                    buttons[0][i].getText().equals(buttons[1][i].getText()) &&
                    buttons[1][i].getText().equals(buttons[2][i].getText())) {
                showWinner(buttons[0][i].getText());
                return;
            }
        }

        // Check diagonals
        if (!buttons[0][0].getText().equals("") &&
                buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][2].getText())) {
            showWinner(buttons[0][0].getText());
            return;
        }

        if (!buttons[0][2].getText().equals("") &&
                buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][0].getText())) {
            showWinner(buttons[0][2].getText());
            return;
        }

        // Check for a draw
        boolean isDraw = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    isDraw = false;
                    break;
                }
            }
        }
        if (isDraw) {
            showWinner("Draw");
        }
    }

    private void showWinner(String winner) {
        String message;
        if (winner.equals("Draw")) {
            message = "It's a draw!";
        } else {
            message = "Congratulations Player " + winner + ", you won the game!";
            updateScore(winner); // Move updateScore here
        }

        // Show message in an alert dialog
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();

        resetBoard(); // Restart game after showing winner
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText(""); // Fixed reset issue
            }
        }
        playerXTurn = true;
    }
private void updateScore(String winner)
{
        if(winner.equals("X"))
        {
            playerXScore++;
            playerXScoreLabel.setText("Player X : " + playerXScore);
        }
        else
        {
            playerOScore++;
            playerOScoreLabel.setText("Player O :" + playerOScore);
        }
}
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(createContent());
        stage.setTitle("Tic Tac Toe!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
