package G07_DeckOfCards;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String startCommand = reader.readLine();
        if ("Card Deck".equals(startCommand)){
            printCardDeck();
        }
    }

    private static void printCardDeck() {
        CardRank[] cardRanks = CardRank.values();
        CardSuit[] cardSuits = CardSuit.values();
        for (CardSuit cardSuit : cardSuits) {
            for (CardRank cardRank : cardRanks) {
                System.out.printf("%s of %s%n",
                        cardRank.toString(),
                        cardSuit.toString());
            }
        }
    }
}
