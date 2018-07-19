package I09_TrafficLights;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<TrafficLights> trafficLights = new ArrayList<>();
        fillTrafficLights(reader, trafficLights);
        iterateTrafficLights(reader, trafficLights);
    }

    private static void iterateTrafficLights(BufferedReader reader, List<TrafficLights> trafficLights) throws IOException {
        int countIterations = Integer.parseInt(reader.readLine());
        for (int i = 0; i < countIterations; i++) {
            String[] currentStates = statMachine(trafficLights);
            System.out.println(String.join(" ", currentStates));
        }
    }

    private static String[] statMachine(List<TrafficLights> trafficLights) {
        String[] currentStates = new String[trafficLights.size()];
        for (int i = 0; i < trafficLights.size(); i++) {
            TrafficLights trafficLight = trafficLights.get(i);
            switch (trafficLight.toString()){
                case "RED":
                    trafficLight = Enum.valueOf(TrafficLights.class, "GREEN");
                    break;
                case "GREEN":
                    trafficLight = Enum.valueOf(TrafficLights.class, "YELLOW");
                    break;
                case "YELLOW":
                    trafficLight = Enum.valueOf(TrafficLights.class, "RED");
                    break;
            }

            trafficLights.remove(i);
            trafficLights.add(i, trafficLight);
            currentStates[i] = trafficLight.toString();
        }

        return currentStates;
    }

    private static void fillTrafficLights(BufferedReader reader, List<TrafficLights> trafficLights) throws IOException {
        String[] trafficLightsStartStates = reader.readLine().split("\\s+");
        for (String trafficLightsStartState : trafficLightsStartStates) {
            TrafficLights trafficLight = Enum.valueOf(TrafficLights.class, trafficLightsStartState.toUpperCase());
            trafficLights.add(trafficLight);
;        }
    }
}
