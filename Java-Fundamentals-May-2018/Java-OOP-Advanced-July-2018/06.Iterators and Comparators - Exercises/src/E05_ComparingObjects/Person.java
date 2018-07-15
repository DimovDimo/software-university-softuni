package E05_ComparingObjects;

public class Person implements Comparable<Person> {

    private String name;
    private int age;
    private String town;

    public Person(String name, int age, String town) {
        this.setName(name);
        this.setAge(age);
        this.setTown(town);
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setAge(int age) {
        this.age = age;
    }

    private void setTown(String town) {
        this.town = town;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getTown() {
        return town;
    }

    @Override
    public int compareTo(Person person) {
        if (this.getName().equals(person.getName())){
            if (this.getAge() == person.getAge()){
                return this.getTown().compareTo(person.getTown());
            } else {
                return this.getAge() - person.getAge();
            }
        } else {
            return this.getName().compareTo(person.getName());
        }
    }
}
