import java.util.Scanner;

public class Blackjack {
    private static final int INITIAL_MONEY = 100;
    private int money;
    private int bet;

    private Deck deck;
    private Hand playerHand;
    private Hand dealerHand;

    public Blackjack() {
        this.money = INITIAL_MONEY;
        this.bet = 0;
        this.deck = new Deck();
        this.playerHand = new Hand();
        this.dealerHand = new Hand();
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nCurrent Money: $" + money);
            System.out.println("Options:");
            System.out.println("1. Place bet");
            System.out.println("2. Play a round");
            System.out.println("3. Quit");

            int choice = getUserChoice(scanner, 3);

            switch (choice) {
                case 1:
                    placeBet(scanner);
                    break;
                case 2:
                    playRound(scanner);
                    break;
                case 3:
                    endGame();
                    return;
            }
        }
    }

    private void placeBet(Scanner scanner) {
        System.out.print("Enter your bet: $");
        int enteredBet = getUserBet(scanner);
        if (enteredBet <= money && enteredBet > 0) {
            bet = enteredBet;
            System.out.println("Bet placed: $" + bet);
        } else {
            System.out.println("Invalid bet. Please enter a valid amount.");
        }
    }

    private int getUserBet(Scanner scanner) {
        int betAmount;
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // consume the invalid input
            }
            betAmount = scanner.nextInt();
        } while (betAmount <= 0);
        return betAmount;
    }

    private void playRound(Scanner scanner) {
        if (bet == 0) {
            System.out.println("Please place a bet before playing a round.");
            return;
        }

        // Clear hands from the previous round
        playerHand.clear();
        dealerHand.clear();

        // Deal initial cards
        playerHand.addCard(deck.drawCard());
        dealerHand.addCard(deck.drawCard());
        playerHand.addCard(deck.drawCard());
        dealerHand.addCard(deck.drawCard());

        System.out.println("\nYour hand:");
        playerHand.display();
        System.out.println("Dealer's hand:");
        System.out.println(dealerHand.display().get(0)); // Display only the first card of the dealer

        // Player's turn
        playPlayerTurn(scanner);

        // Dealer's turn
        if (playerHand.getScore() <= 21) {
            playDealerTurn();
        }

        // Display results and update money
        displayResults();

        // Reset bet for the next round
        bet = 0;
    }

    private void playPlayerTurn(Scanner scanner) {
        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Hit");
            System.out.println("2. Stand");

            int choice = getUserChoice(scanner, 2);

            if (choice == 1) {
                playerHand.addCard(deck.drawCard());
                System.out.println("Your hand:");
                playerHand.display();

                if (playerHand.getScore() > 21) {
                    System.out.println("Bust! Your score is over 21.");
                    break;
                }
            } else {
                break;
            }
        }
    }

    private void playDealerTurn() {
        System.out.println("\nDealer's turn:");

        while (dealerHand.getScore() < 17) {
            dealerHand.addCard(deck.drawCard());
        }

        System.out.println("Dealer's hand:");
        dealerHand.display();
    }

    private void displayResults() {
        System.out.println("\nFinal hands:");
        System.out.println("Your hand:");
        playerHand.display();
        System.out.println("Dealer's hand:");
        dealerHand.display();

        if (playerHand.getScore() > 21) {
            System.out.println("You busted! Dealer wins.");
            money -= bet;
        } else if (dealerHand.getScore() > 21 || playerHand.getScore() > dealerHand.getScore()) {
            System.out.println("Congratulations! You win!");
            money += bet;
        } else if (playerHand.getScore() < dealerHand.getScore()) {
            System.out.println("Dealer wins.");
            money -= bet;
        } else {
            System.out.println("It's a tie! Bet returned.");
        }
    }

    private void endGame() {
        System.out.println("Thank you for playing Blackjack! Final Money: $" + money);
        System.exit(0);
    }

    private int getUserChoice(Scanner scanner, int maxChoice) {
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

    public static void main(String[] args) {
        Blackjack blackjack = new Blackjack();
        blackjack.playGame();
    }
}
