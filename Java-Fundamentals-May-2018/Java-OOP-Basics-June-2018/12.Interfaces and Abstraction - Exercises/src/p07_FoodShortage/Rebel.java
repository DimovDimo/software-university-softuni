package p07_FoodShortage;

public class Rebel extends Society {
    private String name;
    private Integer age;
    private String group;

    public Rebel(String name, Integer age, String group) {
        this.name = name;
        this.age = age;
        this.group = group;
    }

    @Override
    public void buyFood() {
        super.food += 5;
    }
}
