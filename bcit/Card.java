import java.util.Random;

public class Card {
    public static enum TYPE {AIR, WATER, EARTH, FIRE};
    public static enum ACTION {ATTACK, DEFEND, HEAL};

    static private final Random rand = new Random();
    private final TYPE type;
    private final ACTION action;
    private final int value;
    private String name;

    private Card(TYPE t, ACTION a, int d, String n) {
        this.type = t;
        this.action = a;
        this.value = d;
        this.name = n;
    }

    public static Card constructNewCard() {
        int generate = rand.nextInt(0, 6);
        return switch (generate) {
            case 0 -> new Card(TYPE.AIR, ACTION.ATTACK, 5, "Fart");
            case 1 -> new Card(TYPE.FIRE, ACTION.ATTACK, 10, "DrakeSpecial");
            case 2 -> new Card(TYPE.EARTH, ACTION.ATTACK, 3, "Ouch");
            case 3 -> new Card(TYPE.WATER, ACTION.ATTACK, 7, "SuperSoaker");
            case 4 -> new Card(TYPE.AIR, ACTION.DEFEND, -5, "BlowAwau");
            case 5 -> new Card(TYPE.FIRE, ACTION.HEAL, 10, "KarateKidSpecial???");
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

    @Override
    public String toString() {
        return name;
    }
}
