package org.example.game2_in_1;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Title Text
        Text title = new Text("🎮 Welcome to the Game Zone!");
        title.setFont(Font.font("Verdana", 36));
        title.setFill(Color.WHITE);

        // Tic Tac Toe Button
       /* Button ticTacToeBtn = createStyledButton("Play Tic Tac Toe");
        ticTacToeBtn.setOnAction(e -> {
            System.out.println("Tic Tac Toe button clicked!");
        });

        // Matching Cards Button
        Button matchingCardsBtn = createStyledButton("Play Matching Cards");
        matchingCardsBtn.setOnAction(e -> {
            System.out.println("Matching Cards button clicked!");
        });*/


        Button ticTacToeBtn = createStyledButton("Play Tic Tac Toe");

        ticTacToeBtn.setOnAction(e -> {
            TicTacToe ticTacToe = new TicTacToe();
            ticTacToe.start(primaryStage);  // ← reuse the same stage
        });
        Button matchingCardsBtn = createStyledButton("Play Matching Cards");
        matchingCardsBtn.setOnAction(e -> {
            MemoryMatch memoryMatch = new MemoryMatch();
            try {
                memoryMatch.start(primaryStage);  // ← reuse the same stage
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });


        // Layout
        VBox layout = new VBox(30); // spacing increased
        layout.getChildren().addAll(title, ticTacToeBtn, matchingCardsBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new javafx.geometry.Insets(30));

        // Background image (resized to fit new scene size)
      /*  BackgroundImage backgroundImage = new BackgroundImage(
                new Image("file:///D:/MMG%20images/Background_main.png", 800, 600, false, true),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(800, 600, false, false, false, false)
        );

        layout.setBackground(new Background(backgroundImage));*/

        BackgroundImage bg = new BackgroundImage(
                new Image("file:///D:/Game2_in_1/MMG%20images/Background_main.jpg", 800, 600, false, true),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, false)

        );
        layout.setBackground(new Background(bg));

        // Scene
        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setTitle("Game Cover Page");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Helper method to create styled buttons
    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setFont(Font.font("Arial", 18));
        button.setStyle("""
            -fx-background-color: #ffffffcc;
            -fx-background-radius: 15;
            -fx-padding: 12 28;
            -fx-border-color: #34495e;
            -fx-border-radius: 15;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 4, 0, 2, 2);
            -fx-cursor: hand;
        """);

        // Add hover effect
        button.setOnMouseEntered(e -> button.setStyle("""
            -fx-background-color: #87cefa;
            -fx-background-radius: 15;
            -fx-padding: 12 28;
            -fx-border-color: #34495e;
            -fx-border-radius: 15;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 6, 0, 3, 3);
            -fx-cursor: hand;
        """));

        button.setOnMouseExited(e -> button.setStyle("""
            -fx-background-color: #ffffffcc;
            -fx-background-radius: 15;
            -fx-padding: 12 28;
            -fx-border-color: #34495e;
            -fx-border-radius: 15;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 4, 0, 2, 2);
            -fx-cursor: hand;
        """));

        return button;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
