import java.util.Random;

class Character {
    private String name;
    private int health;
    private int agility;
    private int defense;
    private Card[] cards;
    private static Random rand = new Random();
    private Character(String n, int h, int a) {
        this.name = n;
        this.health = h;
        this.agility = a;
        this.cards = new Card[5];
        this.defense = 0;
    }

    public static Character generateEnemyCharacter() {
        int generate = rand.nextInt(0, 6);
        return switch (generate){
            case 0 -> new Character("Naturo", 10, 5);
            case 1 -> new Character("Sakae", 10, 2);
            case 2 -> new Character("Kakashi", 20, 10);
            case 3 -> new Character("RockLee", 15, 15);
            case 4 -> new Character("Yamato", 6, 5);
            case 5 -> new Character("Hero", 17, 7);
            default -> null;
        };
    }
    public int getHealth() {
        return health;
    }

    public int getDefense() {
        return defense;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public void setHealth(int health) {
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