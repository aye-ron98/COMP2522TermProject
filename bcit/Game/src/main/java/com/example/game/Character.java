package com.example.game;

import java.util.Random;

class Character {
    private String name;
    private double health;
    private int agility;
    private double defense;
    private double maxHealth;

    private double defultDefense;
    private Card.TYPE type;
    private Card[] cards;
    private static Random rand = new Random();
    private Character(String n, double h, int a, double d, Card.TYPE t) {
        this.name = n;
        this.health = h;
        this.agility = a;
        this.cards = new Card[5];
        this.defense = d;
        this.defultDefense = d;
        this.maxHealth = h;
        this.type = t;
    }

    public static Character generateEnemyCharacter() {
        int generate = rand.nextInt(0, 6);
        return switch (generate){
            case 0 -> new Character("Naturo", 100, 5, 3, Card.TYPE.AIR);
            case 1 -> new Character("Sakae", 100, 2, 0, Card.TYPE.EARTH);
            case 2 -> new Character("Kakashi", 120, 10, 5, Card.TYPE.FIRE);
            case 3 -> new Character("RockLee", 95, 15, 2, Card.TYPE.FIRE);
            case 4 -> new Character("Yamato", 37, 5, 2, Card.TYPE.EARTH);
            case 5 -> new Character("Hero", 170, 4 , 2, Card.TYPE.FIRE);
            default -> null;
        };
    }
    public double getHealth() {
        return health;
    }

    public double getHealthPercent() { return this.health / this.maxHealth; }

    public double getDefense() {
        return defense;
    }

    public int getAgility() {
        return agility;
    }

    public Card.TYPE getType() {
        return type;
    }

    public double getDEFULT_DEFENSE() {
        return this.defultDefense;
    }

    public double getDefensePercent() { return this.defense / this.defultDefense; }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public void setDefense(double defense) {
        this.defense = defense;
    }

    public void getCards() {
        for (int i = 0; i < cards.length; i++) {
            cards[i] = Card.constructNewCard();
        }
    }

    public Card returnCard(int choice) {
        Card toReturn = this.cards[choice];
        this.cards[choice] = Card.constructNewCard();

        return toReturn;
    }

    public void getCardsForBattle() {
        for (int i = 0; i < cards.length; i++) {
            this.cards[i] = Card.constructNewCard();
        }
    }

    public String getCardName(int select) {
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