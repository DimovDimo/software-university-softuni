import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class D04_FootballStats {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, TreeMap<String, List<String>>> session = new HashMap<>();
        while (true) {
            String line = reader.readLine();
            if ("Season End".equals(line)) {
                break;
            }

            String[] tokens = line.split("( - | result |:)");
            String host = tokens[0];
            String guest = tokens[1];
            String hostPoints = tokens[2];
            String guestPoints = tokens[3];
            addMach(session, host, guest, hostPoints, guestPoints);
            addMach(session, guest, host, guestPoints, hostPoints);
        }

        StringBuilder output = new StringBuilder();
        String[] teams = reader.readLine().split(", ");
        for (String team : teams)
            if (session.containsKey(team)) {
                Map oponent = session.get(team);
                for (Object oponentName : oponent.keySet()) {
                    Object results = oponent.get(oponentName);
                    for (Object result : (ArrayList) results) {
                        output.append(String.format("%s - %s -> %s", team, oponentName, result))
                        .append(System.lineSeparator());
                    }
                }
            }

        System.out.println(output.toString());
    }

    private static void addMach(Map<String, TreeMap<String, List<String>>> session, String host, String guest, String hostPoints, String guestPoints) {
        if (session.containsKey(host) == false) {
            session.put(host, new TreeMap<>());
        }

        if (session.get(host).containsKey(guest) == false) {
            session.get(host)
                    .put(guest, new ArrayList<>());
        }

        session.get(host)
                .get(guest)
                .add(String.format("%s:%s", hostPoints, guestPoints));
    }
}
