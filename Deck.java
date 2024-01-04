import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Deck {
    private final List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        initializeDeck();
        shuffleDeck();
    }

    private void initializeDeck() {
        String[] ranks = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace" };
        String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };

        for (String suit : suits) {
            for (int i = 0; i < ranks.length; i++) {
                int value = (i < 9) ? (i + 2) : 10;
                cards.add(new Card(ranks[i], suit, value));
            }
        }
    }

    private void shuffleDeck() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            System.out.println("Out of cards. Shuffling deck.");
            initializeDeck();
            shuffleDeck();
        }
        return cards.remove(0);
    }
}
