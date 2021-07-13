package craigscode.simulations.skyjo;

import java.util.HashMap;

/**
 * Here is where  all simulations will be managed.
 * Plan:
 *  Simulate 500 games with random choices for cutoffs and rank winners.
 *  Simulate 5 games of each combination of hands (combinations, not permutations) and rank winners.
 */
public class SimulationManager {

    HashMap<Integer, Rank> ranks;

    SimulationManager() {
        ranks = new HashMap<>();
        for (int i = -2; i <= 12; i++) {
            ranks.put(i, new Rank(i));
        }
    }

    void simulateRandomGames(int times) {
        System.out.println("Simulating " + times + " games...");
        long before = System.nanoTime();
        for (int i = 0; i < times; i++) {
            Game game = new Game(4);
            game.simulate();

            // Determine winner
            int winner = 0;
            int score = Integer.MAX_VALUE;
            for (Player player : game.players) {
                if (player.score < score) {
                    winner = player.number;
                    score = player.score;
                }
            }
            for (Player player : game.players) {
                ranks.get(player.cutoff).addStats(player.number == winner, player.score);
            }
        }
        long after = System.nanoTime();

        printRanks();
        System.out.printf("Simulation took %4.3f seconds", (after - before) / 1000000000.0);
    }

    void printRanks() {
        // Sort values first

        int newIndex = 0;

        Rank[] rankedRanks = new Rank[15];
        while (!ranks.isEmpty()) {
            int mostWins = 0;
            int mwCutoff = 0;
            for (Rank rank : ranks.values()) {
                if (rank.wins >= mostWins) {
                    mostWins = rank.wins;
                    mwCutoff = rank.cutoff;
                }
            }
            rankedRanks[newIndex] = ranks.get(mwCutoff);
            ranks.remove(mwCutoff);
            newIndex++;
        }

        for (Rank rank : rankedRanks) {
            System.out.println(rank);
        }
    }

    public static void main(String[] args) {
        SimulationManager manager = new SimulationManager();
        manager.simulateRandomGames(1000000);
    }
}
