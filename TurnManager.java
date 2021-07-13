package craigscode.simulations.skyjo;

public class TurnManager {

    //TODO: Create vertical stacks for cancellation

    Game game;
    int playerCount;
    int currentTurn;
    Card inHand;
    boolean lastRound;

    TurnManager(Game game) {
        this.game = game;
        this.playerCount = game.players.length;
        this.currentTurn = 0;
        this.inHand = new Card(100, "black");
        lastRound = false;
    }

    void turn() {

        if (game.openDeckValue().value <= game.players[currentTurn].cutoff) {
            inHand = game.takeOpenDeck();
            inHandIsBelowCutoff();
        } else {
            inHand = game.takeClosedDeck();
            if (inHand.value <= game.players[currentTurn].cutoff) {
                inHandIsBelowCutoff();
            } else {
                for (int i = 0; i < game.players[currentTurn].hand.length; i++) {
                    if (game.players[currentTurn].hand[i].covered) {
                        game.players[currentTurn].hand[i].uncover();
                        game.players[currentTurn].covered--;
                        break;
                    }
                }
                game.openDeck.add(inHand);
            }
        }

        if (game.players[currentTurn].covered == 0) {
            lastRound = true;
        }
        currentTurn = (currentTurn + 1) % playerCount;

    }

    void inHandIsBelowCutoff() {
        int highest = -10;
        int index = -1;
        for (int i = 0; i < game.players[currentTurn].hand.length; i++) {
            Card current = game.players[currentTurn].hand[i];
            if (!current.covered && current.value > game.players[currentTurn].cutoff && current.value > highest) {
                highest = current.value;
                index = i;
            }
        }

        if (index != -1) {
            Card old = game.players[currentTurn].hand[index];
            game.players[currentTurn].hand[index] = inHand;
            game.openDeck.add(old);
        } else {
            for (int i = 0; i < game.players[currentTurn].hand.length; i++) {
                if (game.players[currentTurn].hand[i].covered) {
                    Card old = game.players[currentTurn].hand[i];
                    old.uncover();
                    game.players[currentTurn].covered--;
                    game.players[currentTurn].hand[i] = inHand;
                    game.openDeck.add(old);
                    break;
                }
            }
        }
    }

    void round() {
        while(!lastRound) {
            turn();
        }
        for (int i = 1; i <= 3; i++) {
            turn();
        }
        //System.out.println("---------- End of Game ----------");
        for (Player player : game.players) {
            player.addScore();
        //    System.out.printf("Player %d : Cutoff %d : Score %d\n", player.number, player.cutoff, player.score);
        }
    }

    void reset() {
        inHand = new Card(100, "black");
        lastRound = false;
        game.reset();
    }

    void game() {
        while(!scoresAboveBounds()) {
            round();
            reset();
        }
    }

    boolean scoresAboveBounds() {
        for (Player player : game.players) {
            if (player.score >= 100) {
                return true;
            }
        }
        return false;
    }

}
