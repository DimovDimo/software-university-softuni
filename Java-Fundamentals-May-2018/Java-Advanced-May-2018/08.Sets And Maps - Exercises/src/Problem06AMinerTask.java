import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class Problem06AMinerTask {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Integer> miner = new LinkedHashMap<>();
        fillMiner(reader, miner);
        printResult(miner);
    }

    private static void printResult(Map<String, Integer> miner) {
        for (Map.Entry<String, Integer> minerEntry : miner.entrySet()) {
            System.out.printf("%s -> %d%n", minerEntry.getKey(), minerEntry.getValue());
        }
    }

    private static void fillMiner(BufferedReader reader, Map<String, Integer> miner) throws IOException {
        while (true) {
            String resource = reader.readLine();
            if ("stop".equals(resource)){
                break;
            }

            int quantity = Integer.parseInt(reader.readLine());
            if (miner.containsKey(resource)){
                int sumResource = miner.get(resource) + quantity;
                miner.put(resource, sumResource);
            } else {
                miner.put(resource, quantity);
            }
        }
    }
}
