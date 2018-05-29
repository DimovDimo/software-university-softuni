import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class Problem12LegendaryFarming {
    public static void main(String[] args) throws IOException {
        Map<String, Integer> dragons = new TreeMap<>();
        Map<String, Integer> outDragons = new TreeMap<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        fillDragonsNames(dragons);
        while (true) {
            String[] elements = reader.readLine().split("\\s+");
            for (int i = 0; i < elements.length; i += 2) {
                int count = Integer.parseInt(elements[i]);
                String matter = elements[i + 1].toLowerCase();
                if (dragonsActions(dragons, outDragons, count, matter)){
                    return;
                }
            }
        }
    }

    private static boolean dragonsActions(Map<String, Integer> dragons, Map<String, Integer> outDragons, int count, String matter) {
        if (dragons.containsKey(matter)) {
            dragons.put(matter, dragons.get(matter) + count);
            if (dragons.get(matter) >= 250) {
                printElement(matter);
                dragons.replace(matter, dragons.get(matter) - 250);
                printDragons(dragons);
                printOutDragons(outDragons);
                return true;
            }
        } else {
            putOutDragons(outDragons, count, matter);
        }

        return false;
    }

    private static void printOutDragons(Map<String, Integer> outDragons) {
        outDragons.forEach((x, y) -> System.out.printf("%s: %s%n", x, y));
    }

    private static void printDragons(Map<String, Integer> dragons) {
        dragons.entrySet()
                .stream()
                .sorted((x, y) -> -x.getValue()
                        .compareTo(y
                                .getValue()))
                .forEach(x -> System.out.printf("%s: %s%n", x.getKey(), x.getValue()));
    }

    private static void printElement(String matter) {
        String element = getElement(matter);
        System.out.println(element + " obtained!");
    }

    private static String getElement(String matter) {
        String element = "";
        switch (matter){
            case "shards":
                element = "Shadowmourne";
                break;
            case "fragments":
                element = "Valanyr";
                break;
            case "motes":
                element = "Dragonwrath";
                break;
        }
        return element;
    }

    private static void putOutDragons(Map<String, Integer> outDragons, int count, String matter) {
        outDragons.putIfAbsent(matter, 0);
        outDragons.put(matter, outDragons.get(matter) + count);
    }

    private static void fillDragonsNames(Map<String, Integer> dragons) {
        String[] names = new String[]{"motes", "shards", "fragments"};
        for (String name : names) {
            dragons.put(name, 0);
        }
    }
}
