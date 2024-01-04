import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Card {
    private final String rank;
    private final String suit;
    private final int value;

    public Card(String rank, String suit, int value) {
        this.rank = rank;
        this.suit = suit;
        this.value = value;
    }

    public String toString() {
        return rank + " of " + suit;
    }

    public int getValue() {
        return value;
    }
}