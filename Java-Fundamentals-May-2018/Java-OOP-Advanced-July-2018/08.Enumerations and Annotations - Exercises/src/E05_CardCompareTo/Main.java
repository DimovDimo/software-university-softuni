package E05_CardCompareTo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        CardRank cardRankFirst = Enum.valueOf(CardRank.class, reader.readLine());
        CardSuit cardSuitFirst = Enum.valueOf(CardSuit.class, reader.readLine());
        int cardPowerFirst = cardRankFirst.getRankPower() + cardSuitFirst.getSuitPower();

        CardRank cardRankSecond = Enum.valueOf(CardRank.class, reader.readLine());
        CardSuit cardSuitSecond = Enum.valueOf(CardSuit.class, reader.readLine());
        int cardPowerSecond = cardRankSecond.getRankPower() + cardSuitSecond.getSuitPower();

        if (cardPowerFirst >= cardPowerSecond){
            printCard(cardRankFirst, cardSuitFirst, cardPowerFirst);
        } else {
            printCard(cardRankSecond, cardSuitSecond, cardPowerSecond);
        }
    }

    private static void printCard(CardRank cardRank, CardSuit cardSuit, int cardPower) {
        System.out.printf("Card name: %s of %s; Card power: %d",
                cardRank.toString(),
                cardSuit.toString(),
                cardPower);
    }
}
