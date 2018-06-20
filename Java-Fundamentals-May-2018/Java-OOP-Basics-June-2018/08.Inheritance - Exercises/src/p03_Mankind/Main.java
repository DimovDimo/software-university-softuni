package p03_Mankind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String[] studentTokens = reader.readLine().split("\\s+");
            String firstNameStudent = studentTokens[0];
            String lastNameStudent = studentTokens[1];
            String facultyNumber = studentTokens[2];
            Student student = new Student(firstNameStudent, lastNameStudent, facultyNumber);

            String[] workerTokens = reader.readLine().split("\\s+");
            String firstNameWorker = workerTokens[0];
            String lastNameWorker = workerTokens[1];
            double weekSalary = Double.parseDouble(workerTokens[2]);
            double workHoursPerDay = Double.parseDouble(workerTokens[3]);
            Worker worker = new Worker(firstNameWorker, lastNameWorker, weekSalary, workHoursPerDay);

            System.out.printf("First Name: %s%n", student.getFirstName());
            System.out.printf("Last Name: %s%n", student.getLastName());
            System.out.printf("Faculty number: %s%n", student.getFacultyNumber());

            System.out.printf("First Name: %s%n", worker.getFirstName());
            System.out.printf("Last Name: %s%n", worker.getLastName());
            System.out.printf("Week Salary: %.2f%n", worker.getWeekSalary());
            System.out.printf("Hours per day: %.2f%n", worker.getWorkHoursPerDay());
            System.out.printf("Salary per hour: %.2f%n", worker.moneyPerHour());

        } catch (IllegalArgumentException error){
            System.out.println(error.getMessage());
        }
    }
}
