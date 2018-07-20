package B02_GettersAndSetters;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Method[] methods = Reflection.class.getDeclaredMethods();
        List<Method> getters = new ArrayList<>();
        List<Method> setters = new ArrayList<>();

        fillGettersAndSetters(methods, getters, setters);
        sortAlphabetically(getters);
        sortAlphabetically(setters);

        printGetters(getters);
        printSetters(setters);
    }

    private static void sortAlphabetically(List<Method> methods) {
        methods.sort((m1, m2) -> m1.getName().compareTo(m2.getName()));
    }

    private static void printSetters(List<Method> setters) {
        for (Method setter : setters) {
            System.out.printf("%s and will set field of %s%n",
                    setter.getName(),
                    setter.getParameterTypes()[0]);
        }
    }

    private static void printGetters(List<Method> getters) {
        for (Method getter : getters) {
            System.out.printf("%s will return %s%n",
                    getter.getName(),
                    getter.getReturnType());
        }
    }

    private static void fillGettersAndSetters(Method[] methods, List<Method> getters, List<Method> setters) {
        for (Method method : methods) {
            String methodName = method.getName();
            if (methodName.startsWith("get")){
                getters.add(method);
            } else if (methodName.startsWith("set")){
                setters.add(method);
            }
        }
    }
}
