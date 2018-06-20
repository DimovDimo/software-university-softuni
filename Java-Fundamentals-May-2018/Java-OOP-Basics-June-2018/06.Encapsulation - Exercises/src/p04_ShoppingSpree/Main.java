package p04_ShoppingSpree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            List<Person> people = getPeople(reader);
            List<Product> products = getProducts(reader);

            while (true){
                String line = reader.readLine();
                if ("END".equals(line)){
                    break;
                }

                String[] tokens = line.split("\\s+");
                String personName = tokens[0];
                String productName = tokens[1];
                shopping(people, products, personName, productName);
            }

            printResult(people);

        } catch (IllegalArgumentException ex){
            System.out.println(ex.getMessage());
        }
    }

    private static void printResult(List<Person> people) {
        for (Person person : people) {
            String productsOutput = person.getProducts().stream()
                    .map(Product::getName)
                    .collect(Collectors.joining(", "));
            if (productsOutput.isEmpty()){
                productsOutput = "Nothing bought";
            }

            System.out.printf("%s - %s%n", person.getName(), productsOutput);
        }
    }

    private static void shopping(List<Person> people, List<Product> products, String personName, String productName) {
        for (Person person : people) {
            if (person.getName().equals(personName)){
                for (Product product : products) {
                    if (product.getName().equals(productName)){
                        if (person.getMoney() - product.getCost() < 0){
                            System.out.printf("%s can't afford %s%n", person.getName(), product.getName());
                        } else {
person.boughtProduct(product);
                            System.out.printf("%s bought %s%n", person.getName(), product.getName());
                        }
                    }
                }
            }
        }
    }

    private static List<Product> getProducts(BufferedReader reader) throws IOException {
        List<Product> products = new LinkedList<>();
        String[] tokensProducts = reader.readLine().split(";");
        for (String tokensProduct : tokensProducts) {
            String[] tokens = tokensProduct.split("=");
            String name = tokens[0];
            double cost = Double.parseDouble(tokens[1]);
            Product product = new Product(name, cost);
            products.add(product);
        }
        return products;
    }

    private static List<Person> getPeople(BufferedReader reader) throws IOException {
        List<Person> people = new LinkedList<>();
        String[] tokensPeople = reader.readLine().split(";");
        for (String tokensPerson : tokensPeople) {
            String[] tokens = tokensPerson.split("=");
            String name = tokens[0];
            double money = Double.parseDouble(tokens[1]);
            Person person = new Person(name, money);
            people.add(person);
        }
        return people;
    }
}
