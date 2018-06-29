package p07_FoodShortage;

public class Citizen extends Society {
    private String name;
    private Integer age;
    private String id;
    private String birthdate;

    public Citizen(String name, Integer age, String id, String birthdate) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.birthdate = birthdate;
    }

    @Override
    public void buyFood() {
        super.food += 10;
    }
}
