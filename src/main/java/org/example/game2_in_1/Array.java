// MyArray.java
package org.example.game2_in_1;

import java.util.Random;

public class Array {
    private final Card[] data;
    private int size;

    public Array(int capacity) {
        data = new Card[capacity];
        size = 0;
    }

    public void add(Card card) {
        if (size < data.length) {
            data[size++] = card;
        }
    }

    public Card get(int index) {
        if (index >= 0 && index < size) {
            return data[index];
        }
        return null;
    }

    public int size() {
        return size;
    }

    public void shuffle() {
        Random rand = new Random();
        for (int i = size - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            Card temp = data[i];
            data[i] = data[j];
            data[j] = temp;
        }
    }
}
