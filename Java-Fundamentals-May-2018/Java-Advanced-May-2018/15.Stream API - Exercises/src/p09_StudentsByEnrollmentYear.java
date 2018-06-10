import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class p09_StudentsByEnrollmentYear {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Student> students = new ArrayList<>();
        while (true){
            String line = reader.readLine();
            if ("END".equals(line)){
                break;
            }

            String[] tokens = line.split("\\s+", 2);
            String facultyNumber = tokens[0];
            String marks = tokens[1];
            if(isEnrolledIn2014Or2015(facultyNumber)){
                Student student = new Student(marks);
                students.add(student);
            }
        }

        students.stream()
                .forEach(System.out::println);
    }

    private static boolean isEnrolledIn2014Or2015(String facultyNumber) {
        if (facultyNumber.endsWith("14") || facultyNumber.endsWith("15")){
            return true;
        } else {
            return false;
        }
    }

    public static class Student {
        private String firstName;
        private String lastName;
        private String facultyNumber;

        public Student(String facultyNumber) {
            this.facultyNumber = facultyNumber;
        }

        @Override
        public String toString() {
            return String.format("%s", this.facultyNumber);
        }
    }
}
