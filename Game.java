package craigscode.simulations.skyjo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class Game {

    ArrayList<Card> closedDeck, openDeck;
    Player[] players;
    TurnManager turnManager;

    Game(int playerCount) {
        initializeDecks();
        // Initialize Players
        players = new Player[playerCount];
        for (int i = 0; i < playerCount; i++) {
            players[i] = new Player(i, initializeHand(), ThreadLocalRandom.current().nextInt(-2, 13));
        }
        // Initialize Turn Manager
        turnManager = new TurnManager(this);
    }

    void simulate() {
        turnManager.game();
    }

    void reset() {
        initializeDecks();
        // Initialize Players
        for (Player player : players) {
            player.reset(initializeHand());
        }
    }

    Card[] initializeHand() {
        Card[] hand = new Card[12];
        for (int j = 0; j < 12; j++) {
            hand[j] = closedDeck.remove(closedDeck.size() - 1);
        }
        // Randomly uncover two cards
        int index1 = ThreadLocalRandom.current().nextInt(12);
        int index2 = ThreadLocalRandom.current().nextInt(12);
        while(index1 == index2) {
            index2 = ThreadLocalRandom.current().nextInt(12);
        }
        hand[index1].uncover();
        hand[index2].uncover();
        return hand;
    }

    void initializeDecks() {
        closedDeck = populateDeck();
        openDeck = new ArrayList<>();

        Card top = closedDeck.remove(closedDeck.size() - 1);
        top.uncover();
        openDeck.add(top);
    }

    Card takeOpenDeck() {
        return openDeck.remove(openDeck.size() - 1);
    }

    Card takeClosedDeck() {
        Card top = closedDeck.remove(closedDeck.size() - 1);
        top.uncover();
        return top;
    }

    Card openDeckValue() {
        return openDeck.get(openDeck.size() - 1);
    }

    void printHands() {
        for (Player player : players) {
            System.out.println(player);
        }
    }

    ArrayList<Card> populateDeck() {
        ArrayList<Card> closedDeck = new ArrayList<>();
        // -2 Cards
        for (int i = 1; i <= 5; i++) {
            closedDeck.add(new Card(-2, "\u001B[35m"));
        }
        // 0 Cards
        for (int i = 1; i <= 15; i++) {
            closedDeck.add(new Card(0, "\u001B[34m"));
        }
        // Green Cards
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 10; j++) {
                closedDeck.add(new Card(i, "\u001B[32m"));
            }
        }
        // Yellow Cards
        for (int i = 5; i <= 8; i++) {
            for (int j = 1; j <= 10; j++) {
                closedDeck.add(new Card(i, "\u001B[33m"));
            }
        }
        // Red Cards
        for (int i = 9; i <= 12; i++) {
            for (int j = 1; j <= 10; j++) {
                closedDeck.add(new Card(i, "\u001B[31m"));
            }
        }
        Collections.shuffle(closedDeck);
        return closedDeck;
    }

}
