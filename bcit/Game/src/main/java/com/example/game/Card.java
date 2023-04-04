package com.example.game;

import java.util.Random;

public final class Card {
    public enum TYPE {
        AIR, WATER, EARTH, FIRE
    };
    public enum ACTION {
        ATTACK, DEFEND, HEAL
    };

    private static final Random rand = new Random();
    private final TYPE type;
    private final ACTION action;
    private final int value;
    private String name;

    private String description;

    private Card(final TYPE t, final ACTION a, final int d, final String n, final String des) {
        this.type = t;
        this.action = a;
        this.value = d;
        this.name = n;
        this.description = des;
    }

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
    public TYPE getType() {
        return type;
    }

    public ACTION getAction() {
        return action;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return name;
    }
}
