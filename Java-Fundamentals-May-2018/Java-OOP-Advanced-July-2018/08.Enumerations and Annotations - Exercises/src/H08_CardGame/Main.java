package H08_CardGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int COUNT_CARDS = 5;
    private static final String OUTPUT_FRAMWORK = "%s wins with %s.";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> playersCards = new ArrayList<>();

        String firstPlayer = reader.readLine();
        String secondPlayer = reader.readLine();
        Card firstPlayerBestCard = getBestCard(reader, playersCards);
        Card secondPlayerBestCard = getBestCard(reader, playersCards);
        printResult(firstPlayer, secondPlayer, firstPlayerBestCard, secondPlayerBestCard);
    }

    private static void printResult(String firstPlayer, String secondPlayer, Card firstPlayerBestCard, Card secondPlayerBestCard) {
        if (firstPlayerBestCard.compareTo(secondPlayerBestCard) >= 0){
            System.out.printf(OUTPUT_FRAMWORK,
                    firstPlayer,
                    firstPlayerBestCard.toString());
        } else {
            System.out.printf(OUTPUT_FRAMWORK,
                    secondPlayer,
                    secondPlayerBestCard.toString());
        }
    }

    private static Card getBestCard(BufferedReader reader, List<String> playersCards) throws IOException {
        Card bestCard = new Card("TWO of CLUBS");
        for (int i = 0; i < COUNT_CARDS; i++) {
            String card = reader.readLine();
            if (playersCards.contains(card)){
                System.out.println("Card is not in the deck.");
                i--;
                continue;
            }

            try {
                Card currentCard = new Card(card);
                if (currentCard.compareTo(bestCard) > 0){
                    bestCard = currentCard;
                }

                playersCards.add(card);

            } catch (IllegalArgumentException exception){
                i--;
                System.out.println(exception.getMessage());
            }
        }

        return bestCard;
    }
}
