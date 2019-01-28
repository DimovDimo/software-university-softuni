import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExtractingCookies {

    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String request = getRequest();
        HttpRequest httpRequest = new HttpRequestImpl(request);

        httpRequest
                .getCookies()
                .forEach(httpCookie -> {
                    System.out.println(
                            String.format("%s <-> %s",
                                    httpCookie.getKey(),
                                    httpCookie.getValue()));
                });
    }

    private static String getRequest() throws IOException {
        StringBuilder result = new StringBuilder();

        String current;
        while ((current = bufferedReader.readLine()) != null &&
                !current.isEmpty()) {
            result.append(current)
                    .append(System.lineSeparator());
        }

        result.append(System.lineSeparator());

        if ((current = bufferedReader.readLine()) != null &&
                !current.isEmpty()) {
            result.append(current);
        }

        return result.toString();
    }
}
