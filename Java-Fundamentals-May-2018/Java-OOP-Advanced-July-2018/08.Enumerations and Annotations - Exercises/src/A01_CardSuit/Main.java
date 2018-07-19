package A01_CardSuit;

public class Main {
    public static void main(String[] args) {
        System.out.println("Card Suits:");
        CardSuit[] cardSuit = CardSuit.values();
        for (CardSuit suit : cardSuit) {
            System.out.printf("Ordinal value: %d; Name value: %s%n",
                    suit.ordinal(),
                    suit.name());
        }
    }
}
