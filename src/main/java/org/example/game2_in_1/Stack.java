// MyStack.java
package org.example.game2_in_1;

public class Stack {
    private Card[] stack;
    private int top;

    public Stack(int size) {
        stack = new Card[size];
        top = -1;
    }

    public void push(Card card) {
        if (top < stack.length - 1) {
            stack[++top] = card;
        }
    }

    public Card pop() {
        if (top >= 0) {
            return stack[top--];
        }
        return null;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int size() {
        return top + 1;
    }

    public void clear() {
        top = -1;
    }

    public Card peek() {
        if (top >= 0) {
            return stack[top];
        }
        return null;
    }

    public Card get(int index) {
        if (index >= 0 && index <= top) {
            return stack[index];
        }
        return null;
    }
}