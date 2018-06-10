import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class p08_WeakStudents {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Student> students = new ArrayList<>();
        while (true){
            String line = reader.readLine();
            if ("END".equals(line)){
                break;
            }

            String[] tokens = line.split("\\s+", 3);
            String firstName = tokens[0];
            String lastName = tokens[1];
            String marks = tokens[2];
            if(leastTwoMarksUnderOrEqualToThree(marks)){
                Student student = new Student(firstName, lastName);
                students.add(student);
            }
        }

        students.stream()
                .forEach(System.out::println);
    }

    private static boolean leastTwoMarksUnderOrEqualToThree(String marks) {
        String[] marksArray = marks.split("\\s+");
        int countLeastMarks = 0;
        for (String mark : marksArray) {
            int currentMarks = Integer.parseInt(mark);
            if (currentMarks <= 3){
                countLeastMarks++;
            }

            if (countLeastMarks == 2){
                return true;
            }
        }

        return false;
    }

    public static class Student {
        private String firstName;
        private String lastName;

        public Student(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        @Override
        public String toString() {
            return String.format("%s %s", this.firstName, this.lastName);
        }
    }
}
