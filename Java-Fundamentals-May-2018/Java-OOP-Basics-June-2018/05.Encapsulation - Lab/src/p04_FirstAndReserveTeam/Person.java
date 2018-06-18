package p04_FirstAndReserveTeam;

public class Person {
    private String firstName;
    private String lastName;
    private int age;
    private double salary;

    public Person(String firstName, String lastName, int age, double salary) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAge(age);
        this.setSalary(salary);
    }

    public int getAge() {
        return age;
    }

    public void setFirstName(String firstName) {
        if (firstName.length() < 3){
            System.out.println("First name cannot be less than 3 symbols");
            throw new IllegalArgumentException("First name cannot be less than 3 symbols");
        }

        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if (lastName.length() < 3){
            System.out.println("Last name cannot be less than 3 symbols");
            throw new IllegalArgumentException("Last name cannot be less than 3 symbols");
        }

        this.lastName = lastName;
    }

    public void setAge(int age) {
        if (age <= 0){
            System.out.println("Age cannot be zero or negative integer");
            throw new IllegalArgumentException("Age cannot be zero or negative integer");
        }

        this.age = age;
    }

    public void setSalary(double salary) {
        if(salary < 460.0){
            System.out.println("Salary cannot be less than 460 leva");
            throw new IllegalArgumentException("Salary cannot be less than 460 leva");
        }

        this.salary = salary;
    }

    public void increaseSalary(double bonus){
        if (this.age > 30){
            this.salary += this.salary * bonus / 100;
        } else {
            this.salary += this.salary * bonus / 200;
        }
    }

    @Override
    public String toString() {
        String salary = this.salary + "";
        salary = trimStringByString(salary, "0");
        return String.format("%s %s gets %s leva", this.firstName, this.lastName, salary);
    }

    public static String trimStringByString(String text, String trimBy) {
        int beginIndex = 0;
        int endIndex = text.length();

        while (text.substring(beginIndex, endIndex).startsWith(trimBy)) {
            beginIndex += trimBy.length();
        }

        while (text.substring(beginIndex, endIndex).endsWith(trimBy)) {
            endIndex -= trimBy.length();
        }

        String outputString = text.substring(beginIndex, endIndex);
        if (outputString.charAt(outputString.length()-1) == '.'){
            outputString += 0;
        }

        return outputString;
    }
}
