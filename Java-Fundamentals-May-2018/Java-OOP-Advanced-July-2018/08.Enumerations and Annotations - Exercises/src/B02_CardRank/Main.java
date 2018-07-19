package B02_CardRank;

public class Main {
    public static void main(String[] args) {
        System.out.println("Card Ranks:");
        CardRank[] cardRanks = CardRank.values();
        for (CardRank rank : cardRanks) {
            System.out.printf("Ordinal value: %d; Name value: %s%n",
                    rank.ordinal(),
                    rank.name());
        }
    }
}
