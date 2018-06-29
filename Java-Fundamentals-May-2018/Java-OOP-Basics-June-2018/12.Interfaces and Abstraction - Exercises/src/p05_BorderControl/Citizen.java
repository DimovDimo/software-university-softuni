package p05_BorderControl;

public class Citizen extends Society {
    private String name;
    private Integer age;

    public Citizen(String name, Integer age, String id) {
        super(id);
        this.name = name;
        this.age = age;
    }
}
