package ca.bcit.comp2522.termproject;

import java.util.Random;

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

    public Character() {
        // todo: make a set of characters for the player to choose from (factory method)
        health = randomize();
        agility = randomize();
        defence = randomize();
        handLimit = STARTING_HAND_LIMIT;

        xp = 0;
    }

    private int randomize() {
        Random rand = new Random();
        return rand.nextInt(10);
    }

    public Player scale() {
    // todo: scale will be most likely giving the enemy a small multiplier on their stats based on how high the player's stats are
        return null;
    }

    public int getXp() {
        return xp;
    }
}
