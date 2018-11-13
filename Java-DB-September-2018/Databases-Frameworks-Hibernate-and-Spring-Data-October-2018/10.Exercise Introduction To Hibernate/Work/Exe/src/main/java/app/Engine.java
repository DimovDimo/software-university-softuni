package app;

import app.entities.Address;
import app.entities.Employee;
import app.entities.Project;
import app.entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Engine implements Runnable{
    private final EntityManager entityManager;
    private Scanner scanner;

    public Engine(EntityManager entityManager, Scanner scanner) {
        this.entityManager = entityManager;
        this.scanner = scanner;
    }

    public void run() {

//        this.containsEmployee();
//        this.employeesWithSalaryOver50000();
//        this.employeesFromDepartment();
//        this.addingNewAddressAndUpdatingEmployee();
//        this.addressesWithEmployeeCount();
//        this.getEmployeeWithProject();
//        this.findLatest10Projects();
//        this.increaseSalaries();
//        this.removeTowns();
//        this.findEmployeesByFirstName();
//        this.employeesMaximumSalaries();
        this.removeObjects();
    }

    /**
     *     2. Remove Objects
     * Use the soft_uni database. Persist all towns from the database. Detach those whose name length is more than 5 symbols. Then transform the names of all attached towns to lowercase and save them to the database.
     */
    private void removeObjects() {
        System.out.println("Problem 2. Remove Objects");

        inTransaction(
                entityManager,
                () -> {
                    List<Town> towns = this.entityManager
                            .createQuery("FROM Town", Town.class)
                            .getResultList();

                    for (Town town : towns) {
                        if (town.getName().length() > 5){
                            this.entityManager.remove(town);
                        }
                    }

                    for (Town town : towns) {
                        town.setName(town.getName().toLowerCase());
                    }

                    for (Town town : towns) {
                        this.entityManager.persist(town);
                    }

                    for (Town town : towns) {
                        System.out.println(town.getId() + " " + town.getName());
                    }
                }
        );


    }

    /**
     *     3. Contains Employee
     * Use the soft_uni database. Write a program that checks if a given employee name is contained in the database.
     */
    private void containsEmployee(){
        System.out.println("Problem 3. Contains Employee");
        System.out.print("Search name: ");
        String name = this.scanner.nextLine();

        this.entityManager.getTransaction().begin();

        try {
            Employee employee = this.entityManager
                    .createQuery(
                            "FROM Employee WHERE concat(first_name, ' ', last_name) = :name",
                            Employee.class
                    )
                    .setParameter("name", name)
                    .getSingleResult();

            System.out.println("Yes");
        } catch (NoResultException nre){
            System.out.println("No");
        }

        this.entityManager.getTransaction().commit();
    }

    /**
     *     4. Employees with Salary Over 50 000
     * Write a program that gets the first name of all employees who have salary over 50 000.
     */
    private void employeesWithSalaryOver50000(){
        System.out.println("Problem 4. Employees with Salary Over 50 000");

        this.entityManager.createQuery(
                    "SELECT e FROM Employee AS e WHERE salary > 50000",
                    Employee.class)
                .getResultList()
                .forEach(e ->
                        System.out.println(e.getFirstName()));
    }

    /**
     *     5. Employees from Department
     * Extract all employees from the Research and Development department. Order them by salary (in ascending order), then by id (in asc order). Print only their first name, last name, department name and salary.
     */
    private void employeesFromDepartment(){
        System.out.println("Problem 5. Employees from Department");

        this.entityManager.createQuery(
                     "SELECT e " +
                       "FROM Employee AS e " +
                       "WHERE e.department.name = 'Research and Development' " +
                       "ORDER BY e.salary, e.id",
                    Employee.class)
                .getResultList()
                .forEach(e ->
                        System.out.printf("%s %s from %s - $%f%n",
                                e.getFirstName(),
                                e.getLastName(),
                                e.getDepartment(),
                                e.getSalary()
                        ));
    }

    /**
     *     6. Adding a New Address and Updating Employee
     * Create a new address with text "Vitoshka 15". Set that address to an employee with a last name, given as an input.
     */
    private void addingNewAddressAndUpdatingEmployee() {
        System.out.println("Problem 6. Adding a New Address and Updating Employee");

        System.out.print("Address to an employee with a last name: ");
        String lastName = this.scanner.nextLine();

        this.entityManager.getTransaction().begin();

        try {
            Address address = new Address();
            address.setText("Vitoshka 15");

            Town town = this.entityManager
                    .createQuery("FROM Town WHERE name = 'Sofia'", Town.class)
                    .getSingleResult();
            address.setTown(town);
            this.entityManager.persist(address);

            Employee employee = this.entityManager
                    .createQuery("FROM Employee WHERE last_name = :lastName", Employee.class)
                    .setParameter("lastName", lastName)
                    .getSingleResult();

            this.entityManager.detach(employee.getAddress());
            employee.setAddress(address);
            this.entityManager.merge(employee);

        } catch (NoResultException nre){
            System.out.println("The last name is not in the DB.");
            this.entityManager.getTransaction().rollback();
        }

        this.entityManager.getTransaction().commit();
    }

    /**
     *     7. Addresses with Employee Count
     * Find all addresses, ordered by the number of employees who live there (descending), then by town id (ascending). Take only the first 10 addresses and print their address text, town name and employee count.
     */
    private void addressesWithEmployeeCount(){
        System.out.println("Problem 7. Addresses with Employee Count");

        this.entityManager.createQuery(
                "SELECT a FROM Address AS a ORDER BY a.employees.size DESC, a.town.id",
                Address.class)
                .setMaxResults(10)
                .getResultList()
                .forEach(a ->
                        System.out.printf("%s, %s - %d employees%n"
                                , a.getText()
                                , a.getTown().getName()
                                , a.getEmployees().size()));
    }

    /**
     *     8. Get Employee with Project
     * Get an employee by his/her id. Print only his/her first name, last name, job title and projects (only their names). The projects should be ordered by name (ascending). The output should be printed in the format given in the example.
     */
    private void getEmployeeWithProject() {
        System.out.println("Problem 8. Get Employee with Project");

        System.out.print("Employee id: ");
        int id = Integer.parseInt(this.scanner.nextLine());

        try {
            Employee employee = this.entityManager
                    .createQuery("FROM Employee WHERE id = :id", Employee.class)
                    .setParameter("id", id)
                    .getSingleResult();

            System.out.printf("%s %s - %s%n",
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getJobTitle());

            List<Project> projects = employee.getProjects().stream()
                    .sorted((p1, p2) -> p1.getName().compareTo(p2.getName()))
                    .collect(Collectors.toList());
            for (Project project : projects) {
                System.out.printf("\t%s%n",
                        project.getName());
            }
        } catch (NoResultException nre){
            System.out.println("The id is not in the DB.");
        }
    }

    /**
     *     9. Find Latest 10 Projects
     * Write a program that prints the last 10 started projects. Print their name, description, start and end date and sort them by name lexicographically. For the output, check the format from the example.
     */
    private void findLatest10Projects() {
        System.out.println("Problem 9. Find Latest 10 Projects");

        this.entityManager.createQuery(
                "SELECT p FROM Project AS p ORDER BY p.startDate DESC",
                Project.class)
                .setMaxResults(10)
                .getResultList()
                .stream()
                .sorted(Comparator.comparing(Project::getName))
                .forEach(p ->
                        System.out.printf("Project name: %s\n" +
                                        " \tProject Description: %s\n" +
                                        " \tProject Start Date:%s\n" +
                                        " \tProject End Date: %s\n",
                                p.getName(),
                                p.getDescription(),
                                p.getStartDate(),
                                p.getEndDate()
                                ));
    }

    /**
     *     10. Increase Salaries
     * Write a program that increases the salaries of all employees, who are in the Engineering, Tool Design, Marketing or Information Services departments by 12%. Then print the first name, the last name and the salary for the employees, whose salary was increased.
     */
    private void increaseSalaries(){
        System.out.println("Problem 10. Increase Salaries");

        this.entityManager.getTransaction().begin();
        this.entityManager
                .createQuery(
                        "SELECT e FROM Employee AS e " +
                          "WHERE department.name IN ('Engineering', 'Tool Design', 'Marketing', 'Information Services') " +
                          "ORDER BY e.id",
                        Employee.class)
                .getResultList()
                .forEach(e -> {
                    e.setSalary(e.getSalary().multiply(BigDecimal.valueOf(1.12)));
                    System.out.printf("%s %s ($%.2f)%n",
                            e.getFirstName(),
                            e.getLastName(),
                            e.getSalary());
                });

        this.entityManager.getTransaction().commit();
    }

    /**
     *     11. Remove Towns
     * Write a program that deletes a town, which name is given as an input. The program should delete all addresses that are in the given town. Print on the console the number of addresses that were deleted. Check the example for the output format.
     */
    private void removeTowns() {
        System.out.println("Problem 11. Remove Towns");

        System.out.print("Town name: ");
        String name = this.scanner.nextLine();

        try {
            Town town = this.entityManager
                    .createQuery("FROM Town WHERE name = :name",
                            Town.class)
                    .setParameter("name", name)
                    .getSingleResult();

            List<Address> addresses = this.entityManager
                    .createQuery("FROM Address WHERE town = :town",
                            Address.class)
                    .setParameter("town", town)
                    .getResultList();

            System.out.println(String.format("%d address%s in %s deleted"
                    , addresses.size()
                    , addresses.size() != 1 ? "es" : ""
                    , town.getName()));

            this.entityManager.getTransaction().begin();

            for (Address address : addresses) {
                for (Employee employee : address.getEmployees()) {
                    employee.setAddress(null);
                }

                address.setTown(null);
                this.entityManager.remove(address);
            }

            this.entityManager.remove(town);

            this.entityManager.getTransaction().commit();

        } catch (NoResultException | IllegalArgumentException e){
            System.out.println("Town name is not in the DB.");
            this.entityManager.getTransaction().rollback();
        }
    }

    /**
     *     12. Find Employees by First Name
     * Write a program that finds all employees, whose first name starts with a pattern given as an input from the console. Print their first and last names, their job title and salary in the format given in the example below.
     */
    private void findEmployeesByFirstName() {
        System.out.println("Problem 12. Find Employees by First Name");

        System.out.print("First name starts with: ");
        String target = this.scanner.nextLine() + "%";

        this.entityManager
                .createQuery("FROM Employee WHERE first_name LIKE :target",
                        Employee.class)
                .setParameter("target", target)
                .getResultList()
                .forEach(e ->
                        System.out.printf("%s %s - %s - ($%s)%n",
                                e.getFirstName(),
                                e.getLastName(),
                                e.getJobTitle(),
                                e.getSalary()));
    }

    /**
     *     13. Employees Maximum Salaries
     * Write a program that finds the max salary for each department. Filter the departments, which max salaries are not in the range between 30000 and 70000.
     */
    private void employeesMaximumSalaries() {
        System.out.println("Problem 13. Employees Maximum Salaries");

        this.entityManager
                .createQuery(
                        "FROM Employee " +
                           "WHERE salary NOT BETWEEN 30000 AND 70000 " +
                           "GROUP BY department.name " +
                           "ORDER BY salary DESC",
                        Employee.class
                )
                .getResultList()
                .stream()
                .sorted(Comparator.comparing(e2 -> e2.getDepartment().getId()))
                .forEach(e ->
                        System.out.printf("%s - %f%n",
                                e.getDepartment().getName(),
                                e.getSalary()));
    }

    private void inTransaction(EntityManager entityManager, Runnable runnable) {
        this.entityManager.getTransaction().begin();
        runnable.run();
        this.entityManager.getTransaction().commit();
    }
}
