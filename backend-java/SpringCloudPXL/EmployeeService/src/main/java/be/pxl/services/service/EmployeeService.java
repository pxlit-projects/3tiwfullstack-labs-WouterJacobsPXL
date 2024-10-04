package be.pxl.services.service;

import be.pxl.services.client.NotificationClient;
import be.pxl.services.domain.Employee;
import be.pxl.services.domain.dto.EmployeeRequest;
import be.pxl.services.domain.dto.EmployeeResponse;
import be.pxl.services.domain.dto.NotificationRequest;
import be.pxl.services.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService implements IEmployeeService{
    private final EmployeeRepository employeeRepository;
    private final NotificationClient notificationClient;
    public void addEmployee(EmployeeRequest employeeRequest) {
        // Validation: throw IllegalArgumentException for invalid input
        if (employeeRequest.getAge() <= 0 || employeeRequest.getAge() > 100) {
            throw new IllegalArgumentException("Invalid employee data");
        }
        if (employeeRequest.getName() == null || employeeRequest.getName().isEmpty()) {
            throw new IllegalArgumentException("Invalid employee data");
        }
        if (employeeRequest.getPosition() == null || employeeRequest.getPosition().isEmpty()) {
            throw new IllegalArgumentException("Invalid employee data");
        }

        Employee employee = Employee.builder()
                .age(employeeRequest.getAge())
                .name(employeeRequest.getName())
                .position(employeeRequest.getPosition())
                .departmentId(employeeRequest.getDepartmentId())
                .organizationId(employeeRequest.getOrganizationId())
                .build();
        employeeRepository.save(employee);

        notificationClient.sendNotification(new NotificationRequest("employee %s created".formatted(employee.getName()),
                "Admin"));
    }


    public EmployeeResponse getEmployeeById(Long id) {
        return employeeRepository.findById(id).map(this::mapToEmployeeResponse)
                .orElseThrow(() -> new NullPointerException("No employee found with id: " + id));
    }

    public List<EmployeeResponse> getAllEmployees() {
        return Optional.of(employeeRepository.findAll()
                        .stream()
                        .map(this::mapToEmployeeResponse)
                        .toList())
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new RuntimeException("No employees found"));
    }


    private EmployeeResponse mapToEmployeeResponse(Employee employee) {
        return EmployeeResponse.builder().age(employee.getAge())
                .name(employee.getName())
                .position(employee.getPosition())
                .departmentId(employee.getDepartmentId())
                .organizationId(employee.getOrganizationId())
                .build();
    }

    public List<EmployeeResponse> getEmployeesByDepartment(Long departmentId) {
        List<EmployeeResponse> employeeResponseList =  employeeRepository.findAll().stream().
                filter(employee -> employee.getDepartmentId().equals(departmentId))
                .toList().stream().map(this::mapToEmployeeResponse).toList();
        if (employeeResponseList.isEmpty()){
            throw new IllegalArgumentException("No employees found with department id: " + departmentId);
        }
        return employeeResponseList;
    }

    public List<EmployeeResponse> getEmployeesByOrganization(Long organizationId) {
        List<EmployeeResponse> employeeResponseList =  employeeRepository.findAll().stream().
                filter(employee -> employee.getOrganizationId().equals(organizationId))
                .toList().stream().map(this::mapToEmployeeResponse).toList();
        if (employeeResponseList.isEmpty()){
            throw new IllegalArgumentException("No employees found with organization id: " + organizationId);
        }
        return employeeResponseList;
    }
}
