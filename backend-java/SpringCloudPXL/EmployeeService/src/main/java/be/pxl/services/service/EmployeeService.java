package be.pxl.services.service;

import be.pxl.services.domain.Employee;
import be.pxl.services.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService implements IEmployeeService{
    private final EmployeeRepository employeeRepository;

    public void addEmployee(Employee employee) {
        //TODO do some checks, convert to dto
        employeeRepository.save(employee);
    }
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("No employee found with id: " + id));
    }


    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}
