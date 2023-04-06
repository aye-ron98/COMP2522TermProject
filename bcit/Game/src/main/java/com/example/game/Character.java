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
    private double health;
    private int agility;
    private double defense;
    private final double maxHealth;
    private final Image characterImage;
    private final double defaultDefense;
    private final Card.TYPE type;
    private Card[] cards;

    /**
     * Create new Character.
     * @param characterName String name of Character
     * @param characterHealth double health of Character
     * @param characterAgility int agility stat of Character
     * @param characterDefense double defense stat of Character
     * @param cardType Type for Character
     * @param characterImage Image for Character
     */
    private Character(final String characterName, final double characterHealth, final int characterAgility,
                      final double characterDefense, final Card.TYPE cardType, final Image characterImage) {
        this.name = characterName;
        this.health = characterHealth;
        this.agility = characterAgility;
        this.cards = new Card[5];
        this.defense = characterDefense;
        this.defaultDefense = characterDefense;
        this.maxHealth = characterHealth;
        this.type = cardType;
        this.characterImage = characterImage;
    }

    /**
     * Generate an enemy Character.
     * @return Character of enemy
     */
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

    /**
     * Get health of Character.
     * @return double of Character health
     */
    public double getHealth() {
        return health;
    }

    /**
     * Get percentage health of Character.
     * @return double of percentage health of Character
     */
    public double getHealthPercent() {
        return this.health / this.maxHealth;
    }

    /**
     * Get defense stat of Character.
     * @return double of defense stat of Character
     */
    public double getDefense() {
        return defense;
    }

    /**
     * Get image sprite of Character.
     * @return Image sprite of Character
     */
    public Image getCharacterImage() {
        return this.characterImage;
    }

    /**
     * Get agility stat of Character.
     * @return int agility stat of Character.
     */
    public int getAgility() {
        return agility;
    }

    /**
     * Get Card element Type.
     * @return Type of Card
     */
    public Card.TYPE getType() {
        return type;
    }

    /**
     * Get the default defense of Character.
     * @return double default defense of Character
     */
    public double getDefaultDefense() {
        return this.defaultDefense;
    }

    /**
     * Get percentage of defense of Character.
     * @return double percentage defense of Character
     */
    public double getDefensePercent() {
        return this.defense / this.defaultDefense;
    }

    /**
     * Set agility stat of Character.
     * @param agility int agility stat
     */
    public void setAgility(final int agility) {
        this.agility = agility;
    }

    /**
     * Set health bar of Character.
     * @param health double health stat of Character
     */
    public void setHealth(final double health) {
        this.health = health;
    }

    /**
     * Set defense stat of Character.
     * @param defense double defense stat of Character
     */
    public void setDefense(final double defense) {
        this.defense = defense;
    }

    /**
     * Get new Cards.
     */
    public void getCards() {
        for (int i = 0; i < cards.length; i++) {
            cards[i] = Card.constructNewCard();
        }
    }

    /**
     * Return Cards to player.
     * @param choice int number of Card choice
     * @return Card chosen by player
     */
    public Card returnCard(final int choice) {
        Card toReturn = this.cards[choice];
        this.cards[choice] = Card.constructNewCard();

        return toReturn;
    }

    /**
     * Construct new Cards for use in Battle.
     */
    public void getCardsForBattle() {
        for (int i = 0; i < cards.length; i++) {
            this.cards[i] = Card.constructNewCard();
        }
    }

    /**
     * Get the name of the Card.
     * @param select int choice of Card
     * @return String name of Card
     */
    public String getCardName(final int select) {
        return this.cards[select].toString();
    }

    /**
     * Remove all current Cards.
     */
    public void clearCards() {
        this.cards = new Card[5];
    }

    /**
     * Return name of Card as String.
     * @return String name of Card
     */
    @Override
    public String toString() {
        return this.name;
    }
}
