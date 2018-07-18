package E05_CodingTracker;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Tracker {

    @Author(name = "Pesho")
    public static void printMethodsByAuthor(Class<?> cl){
        Map<String, List<String>> authors = new TreeMap<>();
        Method[] methods = cl.getDeclaredMethods();

        for (Method method : methods) {
            Author anotation = method.getAnnotation(Author.class);
            if (anotation != null){
                authors.putIfAbsent(anotation.name(), new ArrayList<>());
                authors.get(anotation.name()).add(method.getName() + "()");
            }
        }

        for (Map.Entry<String, List<String>> authorEntry : authors.entrySet()) {
            System.out.println(String.format("%s: %s",
                    authorEntry.getKey(),
                    String.join(", ", authorEntry.getValue())));
        }
    }

    @Author(name = "Pesho")
    public static void main(String[] args) {
        Tracker.printMethodsByAuthor(Tracker.class);
    }
}
