package p04_CompanyRoster;

import java.util.List;

public class Department {
    private String departament;
    private List<Employee> employees;

    public Department(String departament, List<Employee> employees) {
        this.departament = departament;
        this.employees = employees;
    }

    public String getDepartament() {
        return departament;
    }

    public List<Employee> getEmployees() {
        return employees;
    }


}
