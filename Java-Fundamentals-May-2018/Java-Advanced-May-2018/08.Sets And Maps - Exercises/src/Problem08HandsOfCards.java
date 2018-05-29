import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Problem08HandsOfCards {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Set<String>> handsOfCards = new LinkedHashMap<>();
        fillPeople(reader, handsOfCards);
        Map<String, Integer> handsOfCardsValue = new LinkedHashMap<>();
        fillHandsOfCardsValue(handsOfCards, handsOfCardsValue);
        printResult(handsOfCardsValue);
    }

    private static void printResult(Map<String, Integer> handsOfCardsValue) {
        for (Map.Entry<String, Integer> playerEntry : handsOfCardsValue.entrySet()) {
            System.out.printf("%s: %d%n", playerEntry.getKey(), playerEntry.getValue());
        }
    }

    private static void fillHandsOfCardsValue(Map<String, Set<String>> handsOfCards, Map<String, Integer> handsOfCardsValue) {
        for (Map.Entry<String,Set<String>> playerEntry : handsOfCards.entrySet()) {
            Set<String> cards = playerEntry.getValue();
            int cardsValue = getCardsValue(cards);
            handsOfCardsValue.put(playerEntry.getKey(), cardsValue);
        }
    }

    private static Integer getCardsValue(Set<String> cards) {
        int cardsValue = 0;
        for (String card : cards) {
            String type = card.charAt(card.length() - 1) + "";
            int typeValue = 0;
            typeValue = getTypeValue(type, typeValue);
            String[] power = card.split("[SHDC]");
            int powerValue = 0;
            powerValue = getPowerValue(power[0], powerValue);
            cardsValue += powerValue * typeValue;
        }

        return cardsValue;
    }

    private static int getPowerValue(String s, int powerValue) {
        switch (s){
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case "10":
                powerValue = Integer.parseInt(s);
                break;
            case "J":
                powerValue = 11;
                break;
            case "Q":
                powerValue = 12;
                break;
            case "K":
                powerValue = 13;
                break;
            case "A":
                powerValue = 14;
                break;
            default:
                break;
        }
        return powerValue;
    }

    private static int getTypeValue(String type, int typeValue) {
        switch (type){
            case "S":
                typeValue = 4;
                break;
            case "H":
                typeValue = 3;
                break;
            case "D":
                typeValue = 2;
                break;
            case "C":
                typeValue = 1;
                break;
            default:
                break;
        }
        return typeValue;
    }

    private static void fillPeople(BufferedReader reader, Map<String, Set<String>> handsOfCards) throws IOException {
        while (true) {
            String line = reader.readLine();
            if ("JOKER".equals(line)){
                break;
            }

            String[] person = line.split(": ");
            String name = person[0];
            String[] cards = person[1].split(", ");
            Set<String> handCards = new HashSet<>();
            Collections.addAll(handCards, cards);
            if (handsOfCards.containsKey(name)){
                handCards.addAll(handsOfCards.get(name));
                handsOfCards.put(name, handCards);
            } else {
                handsOfCards.put(name, handCards);
            }
        }
    }
}
