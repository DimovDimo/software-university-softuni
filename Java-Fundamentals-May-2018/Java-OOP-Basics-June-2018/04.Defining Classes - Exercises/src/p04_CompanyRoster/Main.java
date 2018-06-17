package p04_CompanyRoster;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int countPeople = Integer.parseInt(reader.readLine());
        Map<String, Department> people = new HashMap<>();
        readPeople(reader, countPeople, people);
        String maxDepartamentSalaryName = getMaxDepartamentSalaryName(people);
        printResult(people, maxDepartamentSalaryName);
    }

    private static void printResult(Map<String, Department> people, String maxDepartamentSalaryName) {
        System.out.printf("Highest Average Salary: %s%n", maxDepartamentSalaryName);
        List<Employee> employees = people.get(maxDepartamentSalaryName).getEmployees();
        List<Employee> employeesSorted = employees.stream()
                .sorted((e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()))
                .collect(Collectors.toList());
        for (Employee employee : employeesSorted) {
            System.out.printf("%s %.2f %s %d%n", employee.getName(), employee.getSalary(),
                    employee.getEmail(), employee.getAge());
        }
    }

    private static String getMaxDepartamentSalaryName(Map<String, Department> people) {
        String maxDepartamentSalaryName = "";
        double maxDepartamentSalarySum = Double.MIN_VALUE;
        for (Map.Entry<String, Department> departament : people.entrySet()) {
            double currentDepartamentSalary = 0;
            for (Employee employee : departament.getValue().getEmployees()) {
                currentDepartamentSalary += employee.getSalary();
            }

            if (maxDepartamentSalarySum < currentDepartamentSalary){
                maxDepartamentSalaryName = departament.getKey();
                maxDepartamentSalarySum = currentDepartamentSalary;
            }
        }
        return maxDepartamentSalaryName;
    }

    private static void readPeople(BufferedReader reader, int countPeople, Map<String, Department> people) throws IOException {
        for (int i = 0; i < countPeople; i++) {
            String[] tokens = reader.readLine().split("\\s+");
            String name = tokens[0];
            double salary = Double.parseDouble(tokens[1]);
            String position = tokens[2];
            String department = tokens[3];
            Employee employee = new Employee(name, salary, position, department);
            checkForEmailAndAge(tokens, employee);
            fillPeople(people, department, employee);
        }
    }

    private static void fillPeople(Map<String, Department> people, String department, Employee employee) {
        if (people.containsKey(department)){
            people.get(department)
                    .getEmployees()
                    .add(employee);
        } else {
            List<Employee> employees = new LinkedList<>();
            employees.add(employee);
            Department currentDepartament = new Department(department, employees);
            people.put(department, currentDepartament);
        }
    }

    private static void checkForEmailAndAge(String[] tokens, Employee employee) {
        if (tokens.length == 5){
            try {
                int age = Integer.parseInt(tokens[4]);
                employee.setAge(age);
            } catch (Exception e){
                String email = tokens[4];
                employee.setEmail(email);
            }

        } else if (tokens.length == 6){
            String email = tokens[4];
            int age = Integer.parseInt(tokens[5]);
            employee.setEmail(email);
            employee.setAge(age);
        }
    }
}


