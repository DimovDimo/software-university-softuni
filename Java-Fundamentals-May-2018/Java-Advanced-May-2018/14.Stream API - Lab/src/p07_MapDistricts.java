import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Predicate;

public class p07_MapDistricts {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, List<Integer>> cities = getCities(reader);
        int bound = Integer.valueOf(reader.readLine());
        cities.entrySet().stream()
                .filter(getFilterPopulationPredicate(bound))
                .sorted(getPopulationComparator())
                .forEach(kv -> {
                    System.out.print(kv.getKey() + ": ");
                    kv.getValue().stream()
                            .sorted((s1, s2) -> s2.compareTo(s1))
                            .limit(5)
                            .forEach(dp -> System.out.print(dp +" "));
                    System.out.println();
                });
    }

    private static Comparator<Map.Entry<String, List<Integer>>> getPopulationComparator() {
        return (kv1, kv2) -> Integer.compare(
                kv2.getValue().stream().mapToInt(Integer::valueOf).sum(),
                kv1.getValue().stream().mapToInt(Integer::valueOf).sum());
    }

    private static Predicate<Map.Entry<String, List<Integer>>> getFilterPopulationPredicate(int bound) {
        return kv -> kv.getValue().stream()
        .mapToInt(Integer::valueOf)
        .sum() >= bound;
    }

    private static HashMap<String, List<Integer>> getCities(BufferedReader reader) throws IOException {
        HashMap<String, List<Integer>> cities = new HashMap<>();
        List<String> tokens = Arrays.asList(reader.readLine().split("\\s+"));
        for (String token : tokens) {
            String[] tokenArgs = token.split(":");
            String city = tokenArgs[0];
            int dostrictPopulation = Integer.valueOf(tokenArgs[1]);
            cities.putIfAbsent(city, new ArrayList<>());
            cities.get(city).add(dostrictPopulation);
        }
        return cities;
    }
}
