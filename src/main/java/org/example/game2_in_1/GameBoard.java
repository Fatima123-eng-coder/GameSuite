package org.example.game2_in_1;

import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import javafx.scene.media.AudioClip;

import java.io.File;

public class GameBoard extends GridPane {

    private final int rows = 4;
    private final int cols = 4;

    private final Stack selectedCards = new Stack(2);
    private final Array cards = new Array(16);

    // FIXED: use File.toURI() to handle spaces in folder name safely
    private final String imageFolderPath = new File("D:/Game2_in_1/MMG images/").toURI().toString();

    private final String backImagePath = imageFolderPath + "BS.PNG";
    private final AudioClip correctSound = new AudioClip(new File("D:/Game2_in_1/MMG images/correct.mp3").toURI().toString());
    private final AudioClip wrongSound = new AudioClip(new File("D:/Game2_in_1/MMG images/incorrect.mp3").toURI().toString());

    private int matchedPairs = 0;
    private boolean gameEnded = false;

    public GameBoard() {

        for (int i = 1; i <= 8; i++) {
            String path = imageFolderPath + "c" + i + ".PNG";
            cards.add(new Card(path));
            cards.add(new Card(path));
        }

        cards.shuffle();

        int index = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Card card = cards.get(index++);
                this.add(createCardView(card), c, r);
            }
        }

        startTimer();
    }

    private StackPane createCardView(Card card) {

        ImageView frontView = new ImageView(
                new Image(card.getImagePath())
        );

        ImageView backView = new ImageView(
                new Image(backImagePath)
        );

        frontView.setFitWidth(80);
        frontView.setFitHeight(80);
        backView.setFitWidth(80);
        backView.setFitHeight(80);

        StackPane cardPane = new StackPane(backView, frontView);
        frontView.setVisible(false);

        cardPane.setOnMouseClicked((MouseEvent e) -> {

            if (!card.isMatched()
                    && selectedCards.size() < 2
                    && !gameEnded
                    && !frontView.isVisible()) {

                frontView.setVisible(true);
                backView.setVisible(false);

                selectedCards.push(card);

                if (selectedCards.size() == 2) {
                    PauseTransition pause = new PauseTransition(Duration.seconds(1));
                    pause.setOnFinished(event -> checkMatch());
                    pause.play();
                }
            }
        });

        card.setFrontView(frontView);
        card.setBackView(backView);

        return cardPane;
    }

    private void checkMatch() {

        Card c1 = selectedCards.get(0);
        Card c2 = selectedCards.get(1);

        if (c1.getImagePath().equals(c2.getImagePath())) {
            c1.setMatched(true);
            c2.setMatched(true);
            matchedPairs++;
            correctSound.play();

            if (matchedPairs == 8) {
                gameEnded = true;
                showAlert("You Win!", "You matched all cards!");
            }

        } else {
            c1.getFrontView().setVisible(false);
            c1.getBackView().setVisible(true);
            c2.getFrontView().setVisible(false);
            c2.getBackView().setVisible(true);

            wrongSound.play();
        }

        selectedCards.clear();
    }

    private void startTimer() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(60), event -> {
                    if (!gameEnded) {
                        gameEnded = true;
                        showAlert("Time's Up!", "Game Over");
                    }
                })
        );
        timeline.setCycleCount(1);
        timeline.play();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }
}