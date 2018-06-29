package p01_DefineAnInterfacePerson;

public class Citizen implements Person {
    private String name;
    private Integer age;

    public Citizen(String name, Integer age) {
        this.name = name;
        this.age = age;
    }


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Integer getAge() {
        return this.age;
    }
}
