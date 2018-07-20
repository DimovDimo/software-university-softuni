package C03_HighQualityMistakes;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Field> fields = Arrays.asList(Reflection.class.getDeclaredFields());
        fields.sort(new Comparator<Field>() {
            @Override
            public int compare(Field o1, Field o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        for (Field field : fields) {
            if (!Modifier.isPrivate(field.getModifiers())){
                System.out.println(field.getName() + " must be private!");
            }
        }

        List<Method> methods = Arrays.asList(Reflection.class.getDeclaredMethods());
        methods.sort((m1, m2) -> m1.getName().compareTo(m2.getName()));
        for (Method method : methods) {
            if (method.getName().startsWith("get")){
                if (method.getParameterTypes().length == 0){
                    if (!Modifier.isPublic(method.getModifiers())){
                        System.out.println(method.getName() + " have to be public!");
                    }
                }
            }
        }

        for (Method method : methods) {
            if (method.getName().startsWith("set")){
//                if (method.getParameterTypes().length == 0){
                    if (!Modifier.isPrivate(method.getModifiers())){
                        System.out.println(method.getName() + " have to be private!");
                    }
//                }
            }
        }
    }

//    ================== OLDEST ==================

//    public static void main(String[] args) {
//        Method[] methods = Reflection.class.getDeclaredMethods();
//        List<Field> fields = new ArrayList<>();
//        List<Method> getters = new ArrayList<>();
//        List<Method> setters = new ArrayList<>();
//
//        fillFieldsGettersAndSetters(methods, fields, getters, setters);
//
//        sortFieldsAlphabetically(fields);
//        sortMethodsAlphabetically(getters);
//        sortMethodsAlphabetically(setters);
//
//        List<String> errors = new ArrayList<>();
//        checkFields(fields, errors);
//        checkGetters(getters, errors);
//        checkSetters(setters, errors);
//
//        if (checkForEnd(errors)){
//            System.out.println("Your code is full of bugs. You don’t understand encapsulation man");
//        }
//    }
//
//    private static boolean checkForEnd(List<String> errors) {
//        if (errors.size() > 4){
//            return true;
//        }
//
//        return false;
//    }
//
//    private static void checkSetters(List<Method> setters, List<String> errors) {
//        for (Method setter : setters) {
//            int modifiers = setter.getModifiers();
//            if (Modifier.isPrivate(modifiers)){
//                String error  = String.format("%s have to be private!",
//                        setter.getName());
//                System.out.println(error);
//                errors.add(error);
//            }
//
//            if (checkForEnd(errors)){
//                return;
//            }
//        }
//    }
//
//    private static void checkGetters(List<Method> getters, List<String> errors) {
//        for (Method getter : getters) {
//            int modifiers = getter.getModifiers();
//            if (Modifier.isPrivate(modifiers)){
//                String error  =String.format("%s have to be public!",
//                        getter.getName());
//                errors.add(error);
//            }
//
//            if (checkForEnd(errors)){
//                return;
//            }
//        }
//    }
//
//    private static void checkFields(List<Field> fields, List<String> errors) {
//        for (Field field : fields) {
//            int modifiers = field.getModifiers();
//            if (Modifier.isPrivate(modifiers)){
//                String error = String.format("%s must be private!",
//                        field.getName());
//                errors.add(error);
//            }
//
//            if (checkForEnd(errors)){
//                return;
//            }
//        }
//    }
//
//    private static void sortMethodsAlphabetically(List<Method> methods) {
//        methods.sort((m1, m2) -> m1.getName().compareTo(m2.getName()));
//    }
//
//    private static void sortFieldsAlphabetically(List<Field> fields) {
//        fields.sort((f1, f2) -> f1.getName().compareTo(f2.getName()));
//    }
//
//    private static void fillFieldsGettersAndSetters(Method[] methods, List<Field> fields, List<Method> getters, List<Method> setters) {
//        for (Method method : methods) {
//            String methodName = method.getName();
//            if (methodName.startsWith("get")){
//                getters.add(method);
//            } else if (methodName.startsWith("set")){
//                setters.add(method);
//            }
//        }
//
//        Field[] fields1Arr = Reflection.class.getDeclaredFields();
//        fields.addAll(Arrays.asList(fields1Arr));
//    }
}
