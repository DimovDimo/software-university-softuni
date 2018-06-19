package p03_AnimalFarm;

public class Chicken {
    private String name;
    private int age;

    public Chicken(String name, int age) {
        this.setName(name);
        this.setAge(age);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if (name == null || name.equals(" ") || name.length() < 1){
            throw new IllegalArgumentException("Name cannot be empty.");
        }

        this.name = name;
    }

    public int getAge() {
        return age;
    }

    private void setAge(int age) {
        if (age < 0 || 15 < age){
            throw new IllegalArgumentException("Age should be between 0 and 15.");
        }

        this.age = age;
    }

    public String productPerDay(){
        if (0 <= this.age && this.age <= 5){
            return "2";
        } else if (6 <= this.age && this.age <= 11){
            return "1";
        } else {
            return "0.75";
        }
    }
}
