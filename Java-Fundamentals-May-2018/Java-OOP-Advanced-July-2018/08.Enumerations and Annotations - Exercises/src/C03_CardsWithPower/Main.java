package C03_CardsWithPower;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        CardRank cardRank = Enum.valueOf(CardRank.class, reader.readLine());
        CardSuit cardSuit = Enum.valueOf(CardSuit.class, reader.readLine());
        int cardPower = cardRank.getRankPower() + cardSuit.getSuitPower();
        System.out.printf("Card name: %s of %s; Card power: %d",
                cardRank.name(),
                cardSuit.name(),
                cardPower);
    }
}
