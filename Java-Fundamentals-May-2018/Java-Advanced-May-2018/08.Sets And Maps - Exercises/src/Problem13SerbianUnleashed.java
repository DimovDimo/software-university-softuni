import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class Problem13SerbianUnleashed {
    public static void main(String[] args) throws IOException {
        Map<String, Map<String, Long>> serbianUnleashed = new LinkedHashMap<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        fillSingers(serbianUnleashed, reader);
        printResult(serbianUnleashed);
    }

    private static void printResult(Map<String, Map<String, Long>> serbianUnleashed) {
        for (Map.Entry<String, Map<String, Long>> singerEntry : serbianUnleashed.entrySet()) {
            System.out.println(singerEntry.getKey());
            singerEntry.getValue()
                    .entrySet()
                    .stream()
                    .sorted((x, y) -> -x.getValue()
                            .compareTo(y
                                    .getValue()))
                    .forEach(singer -> System.out.printf("#  %s -> %d%n", singer.getKey()
                            .trim(), singer.
                            getValue()));
        }
    }

    private static void fillSingers(Map<String, Map<String, Long>> serbianUnleashed, BufferedReader reader) throws IOException {
        while (true) {
            String line = reader.readLine();
            if ("End".equals(line)){
                break;
            }

            Pattern currentPattern = getPattern();
            Matcher matches = makeMatches(line, currentPattern);
            doIfThereAreMatches(serbianUnleashed, matches);
        }
    }

    private static void doIfThereAreMatches(Map<String, Map<String, Long>> serbianUnleashed, Matcher matches) {
        if (matches.matches()) {
            Integer cost = Integer.parseInt(matches.group("p"));
            Integer count = Integer.parseInt(matches.group("c"));
            String serbianSing = matches.group("s");
            String place = matches.group("v");
            putSerbianUnleashed(serbianUnleashed, serbianSing, place, cost, count);
        }
    }

    private static void putSerbianUnleashed(Map<String, Map<String, Long>> serbianUnleashed, String serbianSing, String place, Integer cost, Integer count) {
        serbianUnleashed.putIfAbsent(place, new LinkedHashMap<>());
        serbianUnleashed.get(place).putIfAbsent(serbianSing, 0L);
        serbianUnleashed.get(place).put(serbianSing, serbianUnleashed.get(place).get(serbianSing) + cost * count);
    }

    private static Matcher makeMatches(String line, Pattern currentPattern) {
        return currentPattern.matcher(line);
    }

    private static Pattern getPattern() {
        return Pattern.compile("^(?<s>([^ ]+ ){1,3})@(?<v>([^ ]+ ){1,3})" +
                        "(?<p>\\d+) (?<c>\\d+)$");
    }
}
