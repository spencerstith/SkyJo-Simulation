package craigscode.simulations.skyjo;

public class Rank {

    int cutoff, wins, score, games;

    Rank(int cutoff) {
        this.cutoff = cutoff;
        this.wins = 0;
        this.score = 0;
    }

    void addStats(boolean won, int score) {
        this.score += score;
        if (won) wins++;
        games++;
    }

    public String toString() {
        return String.format("Cutoff: %2d | Wins: %2d | Average Score: %3d", cutoff, wins, score / games);
    }

}
