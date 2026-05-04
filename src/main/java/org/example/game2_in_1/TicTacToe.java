package org.example.game2_in_1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

public class TicTacToe extends Application {
    private Stage primaryStage;
    private Button[][] buttons = new Button[3][3];
    private boolean isPlayerOneTurn = true;
    private boolean isOnePlayerMode = false;
    private Random random = new Random();

    private int player1Score = 0;
    private int player2Score = 0;
    private int drawScore = 0;
    private Label scoreLabel = new Label();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        primaryStage.setResizable(true);
        showMainMenu();
    }

    private void showMainMenu() {
        // Title Label with glow
        Label titleLabel = new Label("TIC TAC TOE GAME");
        titleLabel.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 60));
        titleLabel.setTextFill(Color.WHITE);

        DropShadow glow = new DropShadow();
        glow.setColor(Color.FUCHSIA);
        glow.setOffsetX(0);
        glow.setOffsetY(0);
        glow.setRadius(20);
        titleLabel.setEffect(glow);

        // Buttons
        Button onePlayerBtn = new Button("One Player");
        Button twoPlayerBtn = new Button("Two Player");
        Button backButton = new Button("⬅ Back to Main Menu");

        onePlayerBtn.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        twoPlayerBtn.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        backButton.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        onePlayerBtn.setPrefWidth(200);
        twoPlayerBtn.setPrefWidth(200);
        backButton.setPrefWidth(220);

        // Add glow effect to buttons
        onePlayerBtn.setEffect(glow);
        twoPlayerBtn.setEffect(glow);
        backButton.setEffect(glow);

        // Button styling
        onePlayerBtn.setStyle("-fx-background-color: #3fc564; -fx-text-fill: white;");
        twoPlayerBtn.setStyle("-fx-background-color: #9202b6; -fx-text-fill: white;");
        backButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");

        // Button actions
        onePlayerBtn.setOnAction(e -> startGame(true));
        twoPlayerBtn.setOnAction(e -> startGame(false));
        backButton.setOnAction(e -> {
            HelloApplication mainMenu = new HelloApplication();
            try {
                mainMenu.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Layout
        HBox buttonsBox = new HBox(40, onePlayerBtn, twoPlayerBtn);
        buttonsBox.setAlignment(Pos.CENTER);

        VBox layout = new VBox(50, titleLabel, buttonsBox,backButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));

        // Set background image
        BackgroundImage bgImage = new BackgroundImage(
                new Image("file:D:/Game2_in_1/MMG images/Background_TTT.jpg",800, 600, false, true),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(100, 100, true, true, true, true)
        );
        layout.setBackground(new Background(bgImage));



        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.show();

        player1Score = 0;
        player2Score = 0;
        drawScore = 0;
    }


    private void startGame(boolean isOnePlayer) {
        isOnePlayerMode = isOnePlayer;
        isPlayerOneTurn = true;

        BorderPane root = new BorderPane();

        //backgrond image
        BackgroundImage bgImage = new BackgroundImage(
                new Image("file:D:/Game2_in_1/MMG images/Background_TTT.jpg",800, 600, false, true),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(100, 100, true, true, true, true)
        );
        root.setBackground(new Background(bgImage));

        // Title at the top
        Label titleLabel = new Label("TIC TAC TOE GAME");
        titleLabel.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 60));
        titleLabel.setTextFill(Color.WHITE);

        DropShadow glow = new DropShadow();
        glow.setColor(Color.CYAN);
        glow.setOffsetX(0);
        glow.setOffsetY(0);
        glow.setRadius(20);
        titleLabel.setEffect(glow);

        titleLabel.setPadding(new Insets(10));
        titleLabel.setAlignment(Pos.CENTER);

        HBox titleBox = new HBox(titleLabel);
        titleBox.setAlignment(Pos.CENTER);

        // Back Button
        Button backButton = new Button("← Back");
        backButton.setFont(Font.font(16));
        backButton.setOnAction(e -> showMainMenu());
        backButton.setStyle("-fx-background-color: indianred; -fx-text-fill: white;");
        HBox backBox = new HBox(backButton);
        backBox.setAlignment(Pos.CENTER_LEFT);
        backBox.setPadding(new Insets(5, 20, 5, 20));

        VBox topBox = new VBox(backBox, titleBox);
        root.setTop(topBox);

        // Game Grid
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button btn = new Button();
                btn.setMinSize(120, 120);
                btn.setPrefSize(120, 120);
                btn.setMaxSize(120, 120);
                btn.setFocusTraversable(false);
                final int row = i, col = j;
                btn.setOnAction(e -> handleButtonClick(row, col));
                buttons[i][j] = btn;
                grid.add(btn, j, i);
            }
        }

        root.setCenter(grid);

        // Scoreboard at bottom
        scoreLabel.setText("Player 1 (X): " + player1Score +
                " | " + (isOnePlayerMode ? "Computer" : "Player 2 (O)") + ": " + player2Score +
                " | Draws: " + drawScore);
        scoreLabel.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 24));
        scoreLabel.setTextFill(Color.WHITE);
        scoreLabel.setAlignment(Pos.CENTER);
        scoreLabel.setPadding(new Insets(10));

        DropShadow scoreGlow = new DropShadow();
        scoreGlow.setColor(Color.CYAN);
        scoreGlow.setOffsetX(0);
        scoreGlow.setOffsetY(0);
        scoreGlow.setRadius(15);
        scoreLabel.setEffect(scoreGlow);


        HBox bottomBox = new HBox(scoreLabel);
        bottomBox.setAlignment(Pos.CENTER);
        VBox centerBox = new VBox(20, grid, bottomBox); // 20px spacing between grid and score
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setPadding(new Insets(20, 0, 40, 0)); // adds space below
        root.setCenter(centerBox);


        Scene gameScene = new Scene(root, 900, 700);
        primaryStage.setScene(gameScene);
    }

    private void handleButtonClick(int row, int col) {
        Button btn = buttons[row][col];
        if (btn.getUserData() != null) return;

        String symbol = isPlayerOneTurn ? "X" : "O";
        setButtonGraphic(btn, symbol);
        btn.setUserData(symbol);

        if (checkWin()) {
            showAlert((isPlayerOneTurn ? "Player 1 (X)" : isOnePlayerMode ? "Computer" : "Player 2 (O)") + " Wins!");
            return;
        } else if (isBoardFull()) {
            showAlert("It's a Draw!");
            return;
        }

        isPlayerOneTurn = !isPlayerOneTurn;

        if (isOnePlayerMode && !isPlayerOneTurn) {
            computerMove();
        }
    }

    private void computerMove() {
        int row, col;
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (buttons[row][col].getUserData() != null);

        setButtonGraphic(buttons[row][col], "O");
        buttons[row][col].setUserData("O");

        if (checkWin()) {
            showAlert("Computer Wins!");
        } else if (isBoardFull()) {
            showAlert("It's a Draw!");
        } else {
            isPlayerOneTurn = true;
        }
    }

   /* private void setButtonGraphic(Button btn, String symbol) {
        String fileName = symbol.equals("X") ? "cross.png" : "o.jpeg"; // Make sure your files match this
        Image image = new Image(getClass().getResourceAsStream(fileName));
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(110);
        imageView.setFitHeight(110);
        btn.setGraphic(imageView);
        btn.setDisable(true);
    }*/

    private void setButtonGraphic(Button btn, String symbol) {
        try {
            // Replace with your actual absolute file paths
            String filePath = symbol.equals("X")
                    ? "D:/Game2_in_1/MMG images/cross.jpg"
                    : "D:/Game2_in_1/MMG images/o.jpg";
            FileInputStream input = new FileInputStream(filePath);
            Image image = new Image(input);

            ImageView imageView = new ImageView(image);
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(110);
            imageView.setFitHeight(110);

            btn.setGraphic(imageView);
            btn.setDisable(true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

        private boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getUserData() != null &&
                    buttons[i][0].getUserData().equals(buttons[i][1].getUserData()) &&
                    buttons[i][0].getUserData().equals(buttons[i][2].getUserData())) return true;

            if (buttons[0][i].getUserData() != null &&
                    buttons[0][i].getUserData().equals(buttons[1][i].getUserData()) &&
                    buttons[0][i].getUserData().equals(buttons[2][i].getUserData())) return true;
        }

        if (buttons[0][0].getUserData() != null &&
                buttons[0][0].getUserData().equals(buttons[1][1].getUserData()) &&
                buttons[0][0].getUserData().equals(buttons[2][2].getUserData())) return true;

        if (buttons[0][2].getUserData() != null &&
                buttons[0][2].getUserData().equals(buttons[1][1].getUserData()) &&
                buttons[0][2].getUserData().equals(buttons[2][0].getUserData())) return true;

        return false;
    }

    private boolean isBoardFull() {
        for (Button[] row : buttons) {
            for (Button b : row) {
                if (b.getUserData() == null) return false;
            }
        }
        return true;
    }

    private void showAlert(String message) {
        if (message.contains("Player 1")) {
            player1Score++;
        } else if (message.contains("Player 2") || message.contains("Computer")) {
            player2Score++;
        } else if (message.contains("Draw")) {
            drawScore++;
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.setHeaderText(null);
        alert.setOnHidden(e -> startGame(isOnePlayerMode));
        alert.showAndWait();
    }
}
