package com;

/**
 * A simple class representing a player in our game
 */
public class Player {
    private int score;
    private String name;
    private Parser parser = new Parser();


    /**
     * Creates a player object with the given name and initializes the score to 0
     *
     * @param name the player's name
     */
    public Player(String name) {
        this.score = 0;
        this.name = name;
    }

    /**
     * Constructor for debugging purposes
     * @param name
     * @param score
     */
    public Player(String name, int score) {
        this.score = score;
        this.name = name;
    }

    /**
     * Creates a com.PlayerTest object with a name given by user and initializes the score to 0.
     * Utilizes the com.Parser class
     */
    public Player() {
        this.score = 0;
        this.name = parser.askForName();
    }

    /**
     * Getter for the score attribute.
     *
     * @return the current score of the player.
     */
    public int getScore() {
        return score;
    }

    /**
     * Getter for the name attribute.
     *
     * @return the player's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Prints the player's name, followed by their score.
     */
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
