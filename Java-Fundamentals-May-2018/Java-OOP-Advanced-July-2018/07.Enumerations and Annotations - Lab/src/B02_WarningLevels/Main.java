package B02_WarningLevels;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String targetStatus = reader.readLine();
        Logger logger = new Logger(targetStatus);
        while (true){
            String line = reader.readLine();
            if ("END".equals(line)){
                break;
            }

            String[] tokens = line.split(": ");
            String status = tokens[0];
            String message = tokens[1];
            logger.addMessage(status, message);
        }

        for (Message message : logger.getMessages()) {
            System.out.println(message);
        }
    }
}
