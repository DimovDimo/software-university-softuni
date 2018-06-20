package p03_Mankind;

public class Student extends Human {
    private String facultyNumber;

    public Student(String firstName, String lastName, String facultyNumber) {
        super(firstName, lastName);
        this.setFacultyNumber(facultyNumber);
    }

    public String getFacultyNumber() {
        return facultyNumber;
    }

    private void setFacultyNumber(String facultyNumber) {
        if (5 > facultyNumber.length() || facultyNumber.length() > 10){
            throw new IllegalArgumentException("Invalid faculty number!");
        }

        this.facultyNumber = facultyNumber;
    }
}
