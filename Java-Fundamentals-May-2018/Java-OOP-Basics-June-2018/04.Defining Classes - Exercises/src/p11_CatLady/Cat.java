package p11_CatLady;

public class Cat {
    private String breed;
    private String name;
    private double size;

    public Cat(String breed, String name, double size) {
        this.breed = breed;
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("%s %s %.2f", this.breed, this.name, this.size);
    }
}
