package ca.bcit.comp2522.termproject;

public class Character {
    int STARTING_HAND_LIMIT = 5;

    private int health;
    private int agility;
    private int defence;
    private int handLimit;
//    private enum Type;
//    private Card[];
    String name;
    private int xp;

    public Character(String name) {
        // todo: make a set of characters for the player to choose from
        this.name = name;
        xp = 0;
    }

    public Player scale() {
    // todo: scale will be most likely giving the enemy a small multiplier on their stats based on how high the player's stats are
    return null;
    }

    public int getXp() {
        return xp;
    }
}
