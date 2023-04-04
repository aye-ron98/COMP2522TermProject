package com.example.game;

import javafx.scene.image.Image;

import java.util.Random;

/**
 * com.example.game.Character.
 *
 * @author Aron Zhang
 * @author Lex Wong
 * @version 202213
 */
final class Character {
    private static final Random rand = new Random();
    private final String name;
    private final double health;
    private int agility;
    private double defense;
    private final double maxHealth;
    private final Image characterImage;

    private final double defaultDefense;
    private final Card.TYPE type;
    private Card[] cards;
    private Character(final String n, final double h, final int a, final double d, final Card.TYPE t, final Image i) {
        this.name = n;
        this.health = h;
        this.agility = a;
        this.cards = new Card[5];
        this.defense = d;
        this.defaultDefense = d;
        this.maxHealth = h;
        this.type = t;
        this.characterImage = i;
    }

    public static Character generateEnemyCharacter() {
        int generate = rand.nextInt(0, 6);
        return switch (generate) {
            case 0 -> new Character("Naturo", 100, 5, 3, Card.TYPE.AIR, new Image("C:\\Users\\Admin\\Desktop\\COMP2522TermProject\\bcit\\Game\\src\\main\\resources\\com\\example\\game\\character1.jpg"));
            case 1 -> new Character("Sakae", 100, 2, 0, Card.TYPE.EARTH, new Image("C:\\Users\\Admin\\Desktop\\COMP2522TermProject\\bcit\\Game\\src\\main\\resources\\com\\example\\game\\character2.jpg"));
            case 2 -> new Character("Kakashi", 120, 10, 5, Card.TYPE.FIRE, new Image("C:\\Users\\Admin\\Desktop\\COMP2522TermProject\\bcit\\Game\\src\\main\\resources\\com\\example\\game\\character3.jpg"));
            case 3 -> new Character("RockLee", 95, 15, 2, Card.TYPE.FIRE, new Image("C:\\Users\\Admin\\Desktop\\COMP2522TermProject\\bcit\\Game\\src\\main\\resources\\com\\example\\game\\character4.jpg"));
            case 4 -> new Character("Yamato", 37, 5, 2, Card.TYPE.EARTH, new Image("C:\\Users\\Admin\\Desktop\\COMP2522TermProject\\bcit\\Game\\src\\main\\resources\\com\\example\\game\\character5.jpg"));
            case 5 -> new Character("Hero", 170, 4, 2, Card.TYPE.FIRE, new Image("C:\\Users\\Admin\\Desktop\\COMP2522TermProject\\bcit\\Game\\src\\main\\resources\\com\\example\\game\\character6.jpg"));
            default -> null;
        };
    }
    public double getHealth() {
        return health;
    }

    public double getHealthPercent() {
        return this.health / this.maxHealth;
    }

    public double getDefense() {
        return defense;
    }

    public Image getCharacterImage() {
        return this.characterImage;
    }

    public int getAgility() {
        return agility;
    }

    public Card.TYPE getType() {
        return type;
    }

    public double getDEFAULTDEFENSE() {
        return this.defaultDefense;
    }

    public double getDefensePercent() {
        return this.defense / this.defaultDefense;
    }

    public void setAgility(final int agility) {
        this.agility = agility;
    }

    public void setHealth(final double health) {
        this.health = health;
    }

    public void setDefense(final double defense) {
        this.defense = defense;
    }

    public void getCards() {
        for (int i = 0; i < cards.length; i++) {
            cards[i] = Card.constructNewCard();
        }
    }

    public Card returnCard(final int choice) {
        Card toReturn = this.cards[choice];
        this.cards[choice] = Card.constructNewCard();

        return toReturn;
    }

    public void getCardsForBattle() {
        for (int i = 0; i < cards.length; i++) {
            this.cards[i] = Card.constructNewCard();
        }
    }

    public String getCardName(final int select) {
        return this.cards[select].toString();
    }

    public void clearCards() {
        this.cards = new Card[5];
    }

    @Override
    public String toString() {
        return this.name;
    }
}
