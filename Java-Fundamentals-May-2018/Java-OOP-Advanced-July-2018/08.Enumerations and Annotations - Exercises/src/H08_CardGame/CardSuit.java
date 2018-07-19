package H08_CardGame;

@CardAnnotation(category = "Suit", description = "Provides suit constants for a Card class.")
public enum CardSuit {
    CLUBS(0),
    DIAMONDS(13),
    HEARTS(26),
    SPADES(39);

    private int suitPower;

    CardSuit(int suitPower) {
        this.suitPower = suitPower;
    }

    public int getSuitPower() {
        return suitPower;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
