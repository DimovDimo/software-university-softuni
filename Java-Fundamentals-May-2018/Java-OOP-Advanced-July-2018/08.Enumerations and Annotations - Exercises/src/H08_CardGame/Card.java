package H08_CardGame;

import java.util.Arrays;

public class Card implements Comparable<Card> {
    private CardRank cardRank;
    private CardSuit cardSuit;
    private int cardPower;

    public Card(String card) {
        String[] cardTokens = card.split(" of ");
        String rank = cardTokens[0];
        String suit = cardTokens[1];
        this.setCardRank(rank);
        this.setCardSuit(suit);
        this.setCardPower();
    }

    private void setCardRank(String cardRank) {
        if (isCardRankNotContainsCard(cardRank)){
            throw new IllegalArgumentException("No such card exists.");
        }

        this.cardRank = Enum.valueOf(CardRank.class, cardRank);
    }

    private boolean isCardRankNotContainsCard(String cardRank) {
        boolean isCardRankNotContainsCard = true;
        for (CardRank rank : CardRank.values()) {
            if (rank.toString().equals(cardRank)){
                isCardRankNotContainsCard = false;
                break;
            }
        }

        return isCardRankNotContainsCard;
    }

    private void setCardSuit(String cardSuit) {
        if (isCardSuitNotContainsCard(cardSuit)){
            throw new IllegalArgumentException("No such card exists.");
        }

        this.cardSuit = Enum.valueOf(CardSuit.class, cardSuit);
    }

    private boolean isCardSuitNotContainsCard(String cardSuit) {
        boolean isCardSuitNotContainsCard = true;
        for (CardSuit suit : CardSuit.values()) {
            if (suit.toString().equals(cardSuit)){
                isCardSuitNotContainsCard = false;
                break;
            }
        }

        return isCardSuitNotContainsCard;
    }

    private void setCardPower() {
        this.cardPower = this.cardRank.getRankPower() + this.cardSuit.getSuitPower();
    }

    public int getCardPower() {
        return cardPower;
    }

    @Override
    public int compareTo(Card card) {
        return this.getCardPower() - card.getCardPower();
    }

    @Override
    public String toString() {
        return String.format("%s of %s",
                this.cardRank.toString(),
                this.cardSuit.toString());
    }
}
