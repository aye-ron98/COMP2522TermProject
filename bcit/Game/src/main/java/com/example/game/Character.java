package com.example.game;

import java.util.Random;

class Character {
    private String name;
    private double health;
    private int agility;
    private int defense;
    private double maxHealth;

    private int defultDefense;
    private Card.TYPE type;
    private Card[] cards;
    private static Random rand = new Random();
    private Character(String n, double h, int a, int d, Card.TYPE t) {
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
            case 0 -> new Character("Naturo", 10, 5, 3, Card.TYPE.AIR);
            case 1 -> new Character("Sakae", 10, 2, 0, Card.TYPE.EARTH);
            case 2 -> new Character("Kakashi", 20, 10, 5, Card.TYPE.FIRE);
            case 3 -> new Character("RockLee", 15, 15, 2, Card.TYPE.FIRE);
            case 4 -> new Character("Yamato", 6, 5, 2, Card.TYPE.EARTH);
            case 5 -> new Character("Hero", 17, 4 , 2, Card.TYPE.FIRE);
            default -> null;
        };
    }
    public double getHealth() {
        return health;
    }

    public double getHealthPercent() { return this.maxHealth / this.health; }

    public int getDefense() {
        return defense;
    }

    public int getAgility() {
        return agility;
    }

    public Card.TYPE getType() {
        return type;
    }

    public int getDEFULT_DEFENSE() {
        return this.defultDefense;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public void setDefense(int defense) {
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

    public String displayCards() {
        String toReturn = "\n";
        for (int i = 0; i < cards.length; i++) {
            String temp = i + " " + cards[i].toString() + '\n';
            toReturn = toReturn.concat(temp);
        }
        return toReturn;
    }

    public void getCardsForBattle() {
        for (int i = 0; i < cards.length; i++) {
            this.cards[i] = Card.constructNewCard();
        }
    }

    public String getCardOne() {
        return this.cards[0].toString();
    }
    public String getCardTwo() {
        return this.cards[1].toString();
    }
    public String getCardThree() {
        return this.cards[2].toString();
    }
    public String getCardFour() {
        return this.cards[3].toString();
    }
    public String getCardFive() {
        return this.cards[4].toString();
    }

    public void clearCards() {
        this.cards = new Card[5];
    }

    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                '}';
    }
}