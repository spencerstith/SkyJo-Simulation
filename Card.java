package craigscode.simulations.skyjo;

public class Card {

    int value;
    String color;
    boolean covered;

    Card(int value, String color) {
        this.value = value;
        this.color = color;
        this.covered = true;
    }

    public void uncover() {
        covered = false;
    }

    public String toString() {
        if (covered) {
            return "x";
        } else {
            return color + value + "\u001B[0m";
        }
    }

}
