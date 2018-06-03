import java.util.Scanner;

public class Problem02ParseURL {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String url = scanner.nextLine();
        String[] protocolElements = url.split("://");
        if (protocolElements.length != 2){
            System.out.printf("Invalid URL");
            return;
        }

        String protocol = protocolElements[0];
        String serverElements = protocolElements[1];
        int firstLeftPipe = serverElements.indexOf('/');
        if (firstLeftPipe < 0){
            System.out.printf("Invalid URL");
            return;
        }

        String server = serverElements.substring(0, firstLeftPipe);
        String resourses = serverElements.substring(firstLeftPipe + 1);
        System.out.printf("Protocol = %s%nServer = %s%nResources = %s", protocol, server, resourses);
    }
}
