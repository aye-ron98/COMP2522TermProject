package com.example.game;

import java.util.Random;

/**
 * com.example.game.Card.
 *
 * @author Aron Zhang
 * @author Lex Wong
 * @version 202213
 */
public final class Card {
    /**
     * Elemental type of Cards.
     */
    public enum TYPE {
        AIR, WATER, EARTH, FIRE
    }

    /**
     * Possible Card actions.
     */
    public enum ACTION {
        ATTACK, DEFEND, HEAL
    }

    private static final Random rand = new Random();
    private final TYPE type;
    private final ACTION action;
    private final int value;
    private final String name;

    private final String description;

    private Card(final TYPE cardType, final ACTION cardAction, final int cardValue, final String n, final String des) {
        this.type = cardType;
        this.action = cardAction;
        this.value = cardValue;
        this.name = n;
        this.description = des;
    }

    /**
     * Generate new cards.
     * @return Card that was generated
     */
    //todo: add more cards & change magic numbers
    public static Card constructNewCard() {
        int generate = rand.nextInt(0, 6);
        return switch (generate) {
            case 0 -> new Card(TYPE.AIR, ACTION.ATTACK, 5, "Fart", "an attack");
            case 1 -> new Card(TYPE.FIRE, ACTION.ATTACK, 10, "DrakeSpecial", "an attack");
            case 2 -> new Card(TYPE.EARTH, ACTION.ATTACK, 3, "Ouch", "an attack");
            case 3 -> new Card(TYPE.WATER, ACTION.ATTACK, 7, "SuperSoaker", "an attack");
            case 4 -> new Card(TYPE.AIR, ACTION.DEFEND, -5, "BlowAwau", "a defend");
            case 5 -> new Card(TYPE.FIRE, ACTION.HEAL, 10, "KarateKidSpecial???", "heal move");
            default -> null;
        };
    }

    /**
     * Get Type of Card.
     * @return Type of Card
     */
    public TYPE getType() {
        return type;
    }

    /**
     * Get Action of Card.
     * @return Action of Card
     */
    public ACTION getAction() {
        return action;
    }

    /**
     * Get value of Card.
     * @return int value of Card
     */
    public int getValue() {
        return value;
    }

    /**
     * Get the description of the Card.
     * @return String description of the Card
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Convert the name of the Card to a String.
     * @return String name of the Card
     */
    @Override
    public String toString() {
        return name;
    }
}
