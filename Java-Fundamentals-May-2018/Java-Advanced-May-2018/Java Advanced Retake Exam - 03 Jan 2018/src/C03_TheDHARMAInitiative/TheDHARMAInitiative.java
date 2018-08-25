package C03_TheDHARMAInitiative;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class TheDHARMAInitiative {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Map<String, BigInteger>> stantions = new HashMap<>();

        stantions.put("Hydra", new HashMap<>());
        stantions.put("Arrow", new HashMap<>());
        stantions.put("Flame", new HashMap<>());
        stantions.put("Pearl", new HashMap<>());
        stantions.put("Orchid", new HashMap<>());

        while (true){
            String line = reader.readLine();
            if ("Recruit".equals(line)){
                break;
            }
            
            String[] tokens = line.split(":");
            
            String name = tokens[0];
            BigInteger number = new BigInteger(tokens[1]);
            String stantion = tokens[2];

            if (stantions.containsKey(stantion)){
                stantions.get(stantion).put(name, number);
            }
        }

        String command = reader.readLine();
        if ("DHARMA Initiative".equals(command)){
            stantions.entrySet().stream()
                    .sorted((stantion1, stantion2) -> {
                        int recruitsStantion1 = stantion1.getValue().size();
                        int recruitsStantion2 = stantion2.getValue().size();
                        int comparatorByAmountRecruits = recruitsStantion2 - recruitsStantion1;
                        if (comparatorByAmountRecruits != 0){
                            return comparatorByAmountRecruits;
                        }

                        return stantion1.getKey().compareTo(stantion2.getKey());
                    }).forEach(stantion -> {
                System.out.printf("The %s has %d DHARMA recruits in it.%n", stantion.getKey(), stantion.getValue().size());
            });
        } else if(stantions.containsKey(command)) {
            String stantionInfo = "";
            switch (command){
                case "Hydra": stantionInfo = "The Hydra station: Zoological Research."; break;
                case "Arrow": stantionInfo = "The Arrow station: Development of defensive strategies, and Intelligence gathering."; break;
                case "Flame": stantionInfo = "The Flame station: Communication."; break;
                case "Pearl": stantionInfo = "The Pearl station: Psychological Research and/or Observation."; break;
                case "Orchid": stantionInfo = "The Orchid station: Space-time manipulation research, disguised as a Botanical station."; break;
            }

            StringBuilder output = new StringBuilder();
            output.append(stantionInfo)
                    .append(System.lineSeparator());

            if (stantions.get(command).size() == 0){
                output.append("No recruits.");
            } else {
                stantions.get(command).entrySet().stream()
                        .sorted((r1, r2) -> {
                            return r2.getValue().compareTo(r1.getValue());
                        }).forEach(recruit -> {
                    output.append(String.format("###%s - %s", recruit.getKey(), recruit.getValue()))
                            .append(System.lineSeparator());
                });
            }

            System.out.println(output.toString());

        } else {
            System.out.println("DHARMA Initiative does not have such a station!");
        }
    }
}
