import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Problem10PopulationCounter {
    public static void main(String[] args) throws IOException {
        Map<String, Map<String, Long>> world = new LinkedHashMap<>();
        Map<String, Long> totalPeople = new HashMap<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        fillWorld(world, reader);
        fillTotalPeople(world, totalPeople);
        String totalPopulationString = "total population";
        printResult(world, totalPeople, totalPopulationString);
    }

    private static void printResult(Map<String, Map<String, Long>> world, Map<String, Long> totalPeople, String totalPopulationString) {
        world.entrySet()
                .stream()
                .sorted((firstElement, secondElement) -> -totalPeople.get(firstElement
                        .getKey())
                        .compareTo(totalPeople
                                .get(secondElement
                                        .getKey())))
                .forEach(x -> {
                    System.out.printf("%s (%s: %d)%n", x.getKey(), totalPopulationString, totalPeople.get(x.getKey()));
                    x.getValue()
                            .entrySet()
                            .stream()
                            .sorted((first, second) -> -first
                                    .getValue()
                                    .compareTo(second
                                            .getValue()))
                            .forEach(city -> System.out.printf("=>%s: %d%n", city.getKey(), city.getValue()));
        });
    }

    private static void fillWorld(Map<String, Map<String, Long>> world, BufferedReader reader) throws IOException {
        while (true) {
            String line = reader.readLine();
            if ("report".equals(line)){
                break;
            }

            getElements(world, line);
        }
    }

    private static void fillTotalPeople(Map<String, Map<String, Long>> world, Map<String, Long> totalPeople) {
        for (String country : world.keySet()) {
            totalPeople.put(country, world
                    .get(country)
                    .values()
                    .stream()
                    .mapToLong(Long::valueOf)
                    .sum());
        }
    }

    private static void getElements(Map<String, Map<String, Long>> world, String line) {
        String[] elements = line.split("\\|");
        String town = elements[0];
        String country = elements[1];
        String stringPopulation = elements[2];
        putPopulationInWorld(world, town, country, stringPopulation);
    }

    private static void putPopulationInWorld(Map<String, Map<String, Long>> world, String town, String country, String stringPopulation) {
        long population = Long.parseLong(stringPopulation);
        world.putIfAbsent(country, new LinkedHashMap<>());
        world.get(country)
                .put(town, population);
    }
}
