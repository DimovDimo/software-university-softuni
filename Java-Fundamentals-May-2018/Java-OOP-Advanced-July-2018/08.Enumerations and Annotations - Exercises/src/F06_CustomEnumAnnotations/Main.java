package F06_CustomEnumAnnotations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String targetCategory = reader.readLine();

        List<CardAnnotation> anotations = new ArrayList<>();
        anotations.add(CardRank.class.getAnnotation(CardAnnotation.class));
        anotations.add(CardSuit.class.getAnnotation(CardAnnotation.class));
        for (CardAnnotation anotation : anotations) {
            if (anotation.category().equals(targetCategory)){
                System.out.printf("Type = %s, Description = %s",
                        anotation.type(),
                        anotation.description());
            }
        }
    }
}
