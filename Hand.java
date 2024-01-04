import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Hand {
    private final List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void clear() {
        cards.clear();
    }

    public int getScore() {
        int score = 0;
        int numAces = 0;

        for (Card card : cards) {
            score += card.getValue();
            if (card.getValue() == 11) {
                numAces++;
            }
        }

        while (score > 21 && numAces > 0) {
            score -= 10;
            numAces--;
        }

        return score;
    }

    public void display() {
        for (Card card : cards) {
            System.out.println(card);
        }
    }

    public void displayAt(int pos) {
        System.out.println(cards.get(pos));
    }
}
