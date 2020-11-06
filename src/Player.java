/**
 * A simple class representing a player in our game
 */
public class Player {
    private int score;
    private String name;


    /**
     * Creates a player object with the given name and initializes the score to 0
     *
     * @param name the player's name
     */
    public Player(String name) {
        this.score = 0;
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public void printScore() {
        System.out.printf("%s's score: %d points.%n", name, score);
    }

    /**
     * Updates the score of the player, either by reducing it or increasing it
     *
     * @param points the amount of points to add (if its a positive value) or subtract (if its a negative value)
     */
    public void updateScore(int points) {
        score += points;
    }

}