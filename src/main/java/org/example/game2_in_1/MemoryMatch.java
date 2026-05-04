package org.example.game2_in_1;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.image.Image;

public class MemoryMatch extends Application {
    private VBox root;
    private GameBoard gameBoard;
    private HBox gameBoardContainer;

    @Override
    public void start(Stage primaryStage) {
        // Title Label with Neon Effect
        Label title = new Label("Memory Match Game");
        title.setFont(Font.font("Arial", 36));
        title.setTextFill(Color.HOTPINK);
        DropShadow neonGlow = new DropShadow();
        neonGlow.setColor(Color.HOTPINK);
        neonGlow.setOffsetX(0);
        neonGlow.setOffsetY(0);
        neonGlow.setRadius(30);
        title.setEffect(neonGlow);

        // Game Board
        /*gameBoard = new GameBoard();
        HBox gameBoardContainer = new HBox(gameBoard);
        gameBoardContainer.setAlignment(Pos.CENTER);*/
        // Game Board
        gameBoard = new GameBoard();
        gameBoardContainer = new HBox(gameBoard); // USE FIELD
        gameBoardContainer.setAlignment(Pos.CENTER);



        // Restart Button with Neon Effect
        Button restartButton = new Button("🔄 Restart");
        Button backButton = new Button("⬅ Back to Main Menu");

        restartButton.setFont(Font.font("Arial", 18));
        backButton.setFont(Font.font("Arial", 16));

        restartButton.setTextFill(Color.WHITE);
        backButton.setTextFill(Color.WHITE);

        restartButton.setStyle("-fx-background-color: hotpink;  -fx-border-width: 2px; -fx-padding: 10 20;");
        backButton.setStyle("-fx-background-color: indianred; -fx-padding: 8 16;");

        restartButton.setEffect(neonGlow);
        backButton.setEffect(neonGlow);

        restartButton.setOnAction(e -> restartGame());
        backButton.setOnAction(e -> {
            HelloApplication mainMenu = new HelloApplication();
            try {
                mainMenu.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });


        // Root Layout
        HBox buttonsBox = new HBox(20, backButton, restartButton);
        buttonsBox.setAlignment(Pos.CENTER);

        root = new VBox(30, title, gameBoardContainer,buttonsBox);
        root.setAlignment(Pos.CENTER);
        root.setPrefSize(900, 800);

        // Background Image — FIXED: added "file:" prefix
        BackgroundImage bg = new BackgroundImage(
                new Image("file:D:/Game2_in_1/MMG images/Background_TTT.jpg", 800, 600, false, true),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, false)
        );
        root.setBackground(new Background(bg));


        // Scene & Stage
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Memory Match Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void restartGame() {
        root.getChildren().remove(gameBoardContainer); // REMOVE old container
        gameBoard = new GameBoard();
        gameBoardContainer = new HBox(gameBoard); // CREATE new container
        gameBoardContainer.setAlignment(Pos.CENTER);
        root.getChildren().add(1, gameBoardContainer); // ADD at correct index
    }


    public static void main(String[] args) {
        launch(args);
    }
}