package craigscode.simulations.skyjo;

public class Player {

    int number;
    Card[] hand;
    int score;
    int covered;
    int cutoff;


    Player(int number, Card[] hand, int cutoff) {
        this.number = number;
        this.hand = hand;
        this.score = 0;
        this.covered = 10;
        this.cutoff = cutoff;
    }

    void reset(Card[] hand) {
        this.hand = hand;
        this.covered = 10;
    }

    void addScore() {
        for (Card card : hand) {
            score += card.value;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\u001B[0m");
        sb.append(String.format("Player %d | Cutoff: %d\n", number, cutoff));
        sb.append(String.format("%2s %2s %2s %2s\n", hand[0], hand[1], hand[2], hand[3]));
        sb.append(String.format("%2s %2s %2s %2s\n", hand[4], hand[5], hand[6], hand[7]));
        sb.append(String.format("%2s %2s %2s %2s\n", hand[8], hand[9], hand[10], hand[11]));
        return sb.toString();
    }

}
