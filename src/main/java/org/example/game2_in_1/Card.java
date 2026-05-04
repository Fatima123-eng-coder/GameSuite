package org.example.game2_in_1;

import javafx.scene.image.ImageView;

public class Card {
    private final String imagePath;
    private boolean matched;
    private ImageView frontView;
    private ImageView backView;

    public Card(String imagePath) {
        this.imagePath = imagePath;
        this.matched = false;
    }

    public String getImagePath() {
        return imagePath;
    }

    public boolean isMatched() {
        return matched;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }

    public ImageView getFrontView() {
        return frontView;
    }

    public void setFrontView(ImageView frontView) {
        this.frontView = frontView;
    }

    public ImageView getBackView() {
        return backView;
    }

    public void setBackView(ImageView backView) {
        this.backView = backView;
    }
}
