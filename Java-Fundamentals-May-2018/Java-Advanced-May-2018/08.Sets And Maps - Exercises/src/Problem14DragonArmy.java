import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Problem14DragonArmy {
    public static void main(String[] args) throws IOException {
        Map<String, Map<String, Map<String, Integer>>> dragonArmy = new LinkedHashMap<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int countDragons = Integer.parseInt(reader.readLine());
        fillDragons(dragonArmy, reader, countDragons);
        printResult(dragonArmy);
    }

    private static void printResult(Map<String, Map<String, Map<String, Integer>>> dragonArmy) {
        for (Map.Entry<String, Map<String, Map<String, Integer>>> dragonEntry : dragonArmy.entrySet()) {
            System.out.printf("%s::(%.2f/%.2f/%.2f)%n", dragonEntry.getKey(),
                    getDamage(dragonEntry),
                    getHealth(dragonEntry),
                    dragonEntry.getValue().values().stream().mapToInt(x -> x.get("armor")).average().getAsDouble());
            dragonEntry.getValue().forEach((key, value) -> printDetails(key, value));
        }
    }

    private static PrintStream printDetails(String key, Map<String, Integer> value) {
        return System.out.printf("-%s -> damage: %d, health: %d, armor: %d%n",
                key, value.get("damage"), value.get("health"), value.get("armor"));
    }

    private static double getHealth(Map.Entry<String, Map<String, Map<String, Integer>>> dragonEntry) {
        return dragonEntry.getValue()
                .values()
                .stream()
                .mapToInt(x -> x.get("health"))
                .average()
                .getAsDouble();
    }

    private static double getDamage(Map.Entry<String, Map<String, Map<String, Integer>>> dragonEntry) {
        return dragonEntry.getValue()
                .values()
                .stream()
                .mapToInt(x -> x.get("damage"))
                .average()
                .getAsDouble();
    }

    private static void fillDragons(Map<String, Map<String, Map<String, Integer>>> dragonArmy, BufferedReader reader, int countDragons) throws IOException {
        for (int i = 0; i < countDragons; i++) {
            String[] elements = reader.readLine().split("\\s+");
            String type = elements[0];
            String name = elements[1];
            int damage = getDamage(elements[2]);
            int health = getHealth(elements[3]);
            int armor = getArmor(elements[4]);
            putType(dragonArmy, type);
            putName(dragonArmy, type, name);
            putDamageHealthAndArmor(dragonArmy, type, name, damage, health, armor);
        }
    }

    private static void putDamageHealthAndArmor(Map<String, Map<String, Map<String, Integer>>> dragonArmy, String type, String name, int damage, int health, int armor) {
        dragonArmy.get(type).get(name).put("damage", damage);
        dragonArmy.get(type).get(name).put("health", health);
        dragonArmy.get(type).get(name).put("armor", armor);
    }

    private static void putName(Map<String, Map<String, Map<String, Integer>>> dragonArmy, String type, String name) {
        dragonArmy.get(type).putIfAbsent(name, new LinkedHashMap<>());
    }

    private static void putType(Map<String, Map<String, Map<String, Integer>>> dragonArmy, String type) {
        dragonArmy.putIfAbsent(type, new TreeMap<>());
    }

    private static int getArmor(String element) {
        if (element.equals("null")){
            return 10;
        } else {
            return Integer.parseInt(element);
        }
    }

    private static int getHealth(String element) {
        if (element.equals("null")){
            return 250;
        } else {
            return Integer.parseInt(element);
        }
    }

    private static int getDamage(String element) {
        if (element.equals("null")){
            return 45;
        } else {
            return Integer.parseInt(element);
        }
    }
}
