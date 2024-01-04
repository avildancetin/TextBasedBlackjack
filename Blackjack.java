import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Blackjack {

    private static void dealInitialCards(Deck deck, Hand playerHand, Hand dealerHand) {
        playerHand.addCard(deck.drawCard());
        dealerHand.addCard(deck.drawCard());
        playerHand.addCard(deck.drawCard());
        dealerHand.addCard(deck.drawCard());

        System.out.println("Your hand:");
        playerHand.display();
        System.out.println("Dealer's hand:");
        dealerHand.displayAt(0); // Display only one card of the dealer
    }

    private static void playPlayerTurn(Scanner scanner, Deck deck, Hand playerHand) {
        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Hit");
            System.out.println("2. Stand");
            System.out.println("3. Quit");

            int choice = getUserChoice(scanner, 3);

            if (choice == 1) {
                playerHand.addCard(deck.drawCard());
                System.out.println("Your hand:");
                playerHand.display();

                if (playerHand.getScore() > 21) {
                    System.out.println("Bust! Your score is over 21.");
                    break;
                }
            } else if (choice == 2) {
                break;
            } else {
                System.exit(0);
            }
        }
    }

    private static void playDealerTurn(Deck deck, Hand dealerHand) {
        System.out.println("\nDealer's turn:");

        while (dealerHand.getScore() < 17) {
            dealerHand.addCard(deck.drawCard());
        }

        System.out.println("Dealer's hand:");
        dealerHand.display();
    }

    private static void displayResults(Hand playerHand, Hand dealerHand) {
        System.out.println("\nFinal hands:");
        System.out.println("Your hand:");
        playerHand.display();
        System.out.println("Dealer's hand:");
        dealerHand.display();

        if (playerHand.getScore() > 21) {
            System.out.println("You busted! Dealer wins.");
        } else if (dealerHand.getScore() > 21 || playerHand.getScore() > dealerHand.getScore()) {
            System.out.println("Congratulations! You win!");
        } else if (playerHand.getScore() < dealerHand.getScore()) {
            System.out.println("Dealer wins.");
        } else {
            System.out.println("It's a tie!");
        }
    }

    private static int getUserChoice(Scanner scanner, int maxChoice) {
        int choice;
        do {
            System.out.print("Enter your choice (1-" + maxChoice + "): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // consume the invalid input
            }
            choice = scanner.nextInt();
        } while (choice < 1 || choice > maxChoice);
        return choice;
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Blackjack!");

        Deck deck = new Deck();
        Hand playerHand = new Hand();
        Hand dealerHand = new Hand();

        do {
            dealInitialCards(deck, playerHand, dealerHand);

            playPlayerTurn(scanner, deck, playerHand);

            if (playerHand.getScore() <= 21) {
                playDealerTurn(deck, dealerHand);
            }

            displayResults(playerHand, dealerHand);
        } while (!scanner.nextLine().equals("3"));
        scanner.close();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Blackjack!");

        Deck deck = new Deck();
        Hand playerHand = new Hand();
        Hand dealerHand = new Hand();

        dealInitialCards(deck, playerHand, dealerHand);

        playPlayerTurn(scanner, deck, playerHand);

        if (playerHand.getScore() <= 21) {
            playDealerTurn(deck, dealerHand);
        }

        displayResults(playerHand, dealerHand);

        scanner.close();
    }
}