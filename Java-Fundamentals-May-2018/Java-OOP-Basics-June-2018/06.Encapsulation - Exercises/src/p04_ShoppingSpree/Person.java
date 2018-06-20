package p04_ShoppingSpree;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Person {
    private String name;
    private double money;
    private List<Product> products;

    public Person(String name, double money) {
        this.setName(name);
        this.setMoney(money);
        this.products = new LinkedList<>();
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(this.products);
    }

    public String getName() {
        return name;
    }

    public double getMoney() {
        return money;
    }

    private void setName(String name) {
        if (name == null || name.equals(" ")){
            throw new IllegalArgumentException("Name cannot be empty");
        }

        this.name = name;
    }

    private void setMoney(double money) {
        if (money < 0){
            throw new IllegalArgumentException("Money cannot be negative");
        }

        this.money = money;
    }

    public void boughtProduct(Product product){
        this.money -= product.getCost();
        this.products.add(product);
    }
}
