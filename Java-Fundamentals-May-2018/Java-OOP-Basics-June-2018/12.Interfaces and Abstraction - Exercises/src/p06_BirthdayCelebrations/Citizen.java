package p06_BirthdayCelebrations;

public class Citizen extends Society {
    private String name;
    private Integer age;

    public Citizen(String name, Integer age, String id, String birthdate) {
        super(id, birthdate);
        this.name = name;
        this.age = age;
    }
}
