package be.pxl.services.service;

import be.pxl.services.domain.Employee;
import be.pxl.services.domain.dto.EmployeeRequest;
import be.pxl.services.domain.dto.EmployeeResponse;
import be.pxl.services.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmployeeService implements IEmployeeService{
    private final EmployeeRepository employeeRepository;

    public void addEmployee(EmployeeRequest employeeRequest) {
        //TODO do some checks, convert to dto
        Employee employee = Employee.builder().age(employeeRequest.getAge())
                                                .name(employeeRequest.getName())
                                                .position(employeeRequest.getPosition())
                                                .departmentId(employeeRequest.getDepartmentId())
                                                .organizationId(employeeRequest.getOrganizationId())
                                                .build();
        employeeRepository.save(employee);
    }
    public EmployeeResponse getEmployeeById(Long id) {
        return employeeRepository.findById(id).map(this::mapToEmployeeResponse)
                .orElseThrow(() -> new NullPointerException("No employee found with id: " + id));
    }


    public List<EmployeeResponse> getAllEmployees() {
        return employeeRepository.findAll().stream().map(this::mapToEmployeeResponse).toList();
    }

    private EmployeeResponse mapToEmployeeResponse(Employee employee) {
        return EmployeeResponse.builder().age(employee.getAge())
                .name(employee.getName())
                .position(employee.getPosition())
                .departmentId(employee.getDepartmentId())
                .organizationId(employee.getOrganizationId())
                .build();
    }
}
