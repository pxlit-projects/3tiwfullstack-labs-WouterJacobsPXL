package be.pxl.services.service;

import be.pxl.services.domain.Employee;
import be.pxl.services.domain.dto.EmployeeRequest;
import be.pxl.services.domain.dto.EmployeeResponse;
import be.pxl.services.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService implements IEmployeeService{
    private final EmployeeRepository employeeRepository;

    public void addEmployee(EmployeeRequest employeeRequest) {
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

    public List<EmployeeResponse> getEmployeeByDepartment(Long departmentId) {
        List<EmployeeResponse> employeeResponseList =  employeeRepository.findAll().stream().
                filter(employee -> employee.getDepartmentId().equals(departmentId))
                .toList().stream().map(this::mapToEmployeeResponse).toList();
        if (employeeResponseList.isEmpty()){
            throw new IllegalArgumentException("No employee found with department id: " + departmentId);
        }
        return employeeResponseList;
    }

    public List<EmployeeResponse> getEmployeeByOrganization(Long organizationId) {
        List<EmployeeResponse> employeeResponseList =  employeeRepository.findAll().stream().
                filter(employee -> employee.getOrganizationId().equals(organizationId))
                .toList().stream().map(this::mapToEmployeeResponse).toList();
        if (employeeResponseList.isEmpty()){
            throw new IllegalArgumentException("No employee found with organization id: " + organizationId);
        }
        return employeeResponseList;
    }
}
