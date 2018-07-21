package B02_BlackBoxInteger.pr02PrivateClassFiddling;

import B02_BlackBoxInteger.pr02PrivateClassFiddling.com.BlackBoxInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    private static final String className = "BlackBoxInt";
    private static final String classPath = "B02_BlackBoxInteger.pr02PrivateClassFiddling.com.";
    private static final String fieldInnerValueName = "innerValue";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        BlackBoxInt blackBoxInt = null;
        try {
            Class<?> blackBoxIntClass = Class.forName(classPath + className);
            Constructor<?> declaredConstructor = blackBoxIntClass.getDeclaredConstructor();
            declaredConstructor.setAccessible(true);
            blackBoxInt = (BlackBoxInt) declaredConstructor.newInstance();
            declaredConstructor.setAccessible(false);
        } catch (ClassNotFoundException |
                NoSuchMethodException |
                IllegalAccessException |
                InstantiationException |
                InvocationTargetException e) {
            e.printStackTrace();
        }

        try {
            while (true) {
                String line = reader.readLine();
                if ("END".equals(line)) {
                    break;
                }


                String[] tokens = line.split("_");
                String methodName = tokens[0];
                int methodInput = Integer.parseInt(tokens[1]);

                Method declaredMethod = blackBoxInt.getClass()
                        .getDeclaredMethod(methodName, int.class);
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(blackBoxInt, methodInput);

                Field innerValue = blackBoxInt.getClass()
                        .getDeclaredField(fieldInnerValueName);
                innerValue.setAccessible(true);
                System.out.println(innerValue.get(blackBoxInt));

            }
        } catch (NoSuchMethodException |
                IllegalAccessException |
                NoSuchFieldException |
                InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
