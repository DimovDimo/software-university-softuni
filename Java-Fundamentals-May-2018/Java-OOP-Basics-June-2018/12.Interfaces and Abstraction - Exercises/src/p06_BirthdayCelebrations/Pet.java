package p06_BirthdayCelebrations;

public class Pet extends Society {
    private String name;

    public Pet(String name, String birthdate) {
        super(null, birthdate);
        this.name = name;
    }
}
