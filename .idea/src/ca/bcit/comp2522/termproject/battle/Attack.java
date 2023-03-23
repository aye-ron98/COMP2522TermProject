package battle;

public class Attack extends abstractMove {
    private int damage;
    Attack(final Card c) {
        super(c);
        this.damage =
    }

    public void attack(Character p, Character e) {
        e.setHealth(e.getHealth - this.damage);
    }
}
