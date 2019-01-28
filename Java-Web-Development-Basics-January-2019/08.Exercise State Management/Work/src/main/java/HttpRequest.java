import java.util.List;
import java.util.Map;

public interface HttpRequest {
    String getMethod();

    String getRequestUrl();

    Map<String, String> getHeaders();

    Map<String, String> getBodyParams();

    List<HttpCookie> getCookies();

    boolean isResource();

    void setMethod(String method);

    void setRequestUrl(String requestUrl);

    void addHeader(String key, String value);

    void addBodyParams(String key, String value);

    void addCookie(HttpCookie cookie);
}
