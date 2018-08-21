import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class D04_GUnit {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Map<String, Set<String>>> qa = new HashMap<>();
        Pattern pattern = Pattern.compile("^([A-Z][A-Za-z0-9]+) \\| ([A-Z][A-Za-z0-9]+) \\| ([A-Z][A-Za-z0-9]+)$");

        while (true){
            String line = reader.readLine();
            if ("It's testing time!".equals(line)){
                break;
            }

            Matcher matcher = pattern.matcher(line);
            if (matcher.find()){
                String className = matcher.group(1);
                String methodName = matcher.group(2);
                String unitTestName = matcher.group(3);

                if (qa.containsKey(className) == false){
                    qa.put(className, new HashMap<>());
                }

                if (qa.get(className).containsKey(methodName) == false){
                    qa.get(className).put(methodName, new HashSet<>());
                }

                qa.get(className).get(methodName).add(unitTestName);
            }
        }

        StringBuilder result = new StringBuilder();
        qa.entrySet().stream()
                .sorted((clazz1, clazz2) -> {
                    int amountUnitTestClazz1 = clazz1.getValue().values().stream()
                            .mapToInt(unitTest -> unitTest.size()).sum();
                    int amountUnitTestClazz2 = clazz2.getValue().values().stream()
                            .mapToInt(unitTest -> unitTest.size()).sum();

                    int comparatorAmountOfUnitTesting = amountUnitTestClazz2 - amountUnitTestClazz1;
                    if (comparatorAmountOfUnitTesting != 0){
                        return comparatorAmountOfUnitTesting;
                    }

                    int amountMethodsClazz1 =  clazz1.getValue().size();
                    int amountMethodsClazz2 =  clazz2.getValue().size();

                    int comparatorAmountOfMethods = amountMethodsClazz1 - amountMethodsClazz2;
                    if (comparatorAmountOfMethods != 0){
                        return comparatorAmountOfMethods;
                    }

                    return clazz1.getKey().compareTo(clazz2.getKey());
                }).forEach(clazz -> {
                    result.append(String.format("%s:", clazz.getKey())).append(System.lineSeparator());
                    clazz.getValue().entrySet().stream()
                            .sorted((method1, method2) -> {
                                int amountUnitTestsMethod1 = method1.getValue().size();
                                int amountUnitTestsMethod2 = method2.getValue().size();

                                int comparatorByamountUnitTests = amountUnitTestsMethod2 - amountUnitTestsMethod1;
                                if (comparatorByamountUnitTests != 0){
                                    return comparatorByamountUnitTests;
                                }

                                return method1.getKey().compareTo(method2.getKey());
                            }).forEach(method -> {
                        result.append(String.format("##%s", method.getKey())).append(System.lineSeparator());

                        method.getValue().stream()
                                .sorted((unitTest1, unitTest2) -> {
                                    int lenghtOdNameUnitTest1 = unitTest1.length();
                                    int lenghtOdNameUnitTest2 = unitTest2.length();

                                    int comparatorByLenghtOfNames = lenghtOdNameUnitTest1 - lenghtOdNameUnitTest2;
                                    if (comparatorByLenghtOfNames != 0){
                                        return comparatorByLenghtOfNames;
                                    }

                                    return unitTest1.compareTo(unitTest2);
                                }).forEach(unitTest -> {
                            result.append(String.format("####%s", unitTest)).append(System.lineSeparator());
                        });
                    });
        });

        System.out.println(result.toString());
    }
}
