package battle;

public class abstractMove implements move {
    final private Card card;
    abstractMove(final Card c) {
        this.card = c;
    }

    public final Card.TYPE getCardType() {
        return this.card.getType();
    }



}
