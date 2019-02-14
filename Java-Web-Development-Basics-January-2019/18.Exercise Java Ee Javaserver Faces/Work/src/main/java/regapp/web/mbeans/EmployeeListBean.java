package regapp.web.mbeans;

import org.modelmapper.ModelMapper;
import regapp.domain.models.service.EmployeeServiceModel;
import regapp.domain.models.view.EmployeeListViewModel;
import regapp.domain.models.view.EmployeeViewModel;
import regapp.service.EmployeeService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class EmployeeListBean {

    private List<EmployeeListViewModel> employees;

    private EmployeeService employeeService;

    private ModelMapper modelMapper;

    public EmployeeListBean() {

    }

    @Inject
    public EmployeeListBean(EmployeeService employeeService, ModelMapper modelMapper) {
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
        this.employees = this.employeeService
                .findAllEmployees().stream()
                .map(employee ->
                        this.modelMapper.map(
                                employee, EmployeeListViewModel.class))
                .collect(Collectors.toList());
    }

    public List<EmployeeListViewModel> getEmployees() {
        return this.employees;
    }

    public void setEmployees(List<EmployeeListViewModel> employees) {
        this.employees = employees;
    }

    public BigDecimal getTotalMoney() {
        return this.employees
                .stream()
                .map(EmployeeListViewModel::getSalary)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getAverageSalary() {
        if (this.employees.size() == 0){
            return BigDecimal.ZERO;
        } else {
            return this.getTotalMoney()
                    .divide(
                            new BigDecimal(
                                    this.employees.size()),
                            RoundingMode.HALF_UP);
        }
    }
}
