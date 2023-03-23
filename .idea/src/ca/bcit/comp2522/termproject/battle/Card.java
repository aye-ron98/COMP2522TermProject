package battle;

public class Card {
    enum TYPE {WATER, AIR, FIRE, EARTH};
    private enum ACTION {ATTACK, DEFENSE, HEAL};

    private TYPE type;
    private ACTION action;
    private int value;

    Card(TYPE t, ACTION a, int d) {
        this.type = t;
        this.action = a;
        this.value = d;
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
}
