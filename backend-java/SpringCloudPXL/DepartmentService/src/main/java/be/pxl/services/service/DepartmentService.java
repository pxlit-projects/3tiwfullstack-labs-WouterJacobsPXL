package be.pxl.services.service;

import be.pxl.services.client.EmployeeClient;
import be.pxl.services.domain.Department;
import be.pxl.services.domain.Employee;
import be.pxl.services.domain.dto.DepartmentRequest;
import be.pxl.services.domain.dto.DepartmentResponse;
import be.pxl.services.domain.dto.EmployeeResponse;
import be.pxl.services.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
//TODO REWRITE AFTER QUESTION
@Service
@RequiredArgsConstructor
public class DepartmentService implements IDepartmentService {
    private final DepartmentRepository departmentRepository;
    private final EmployeeClient employeeClient;

    public void addDepartment(DepartmentRequest departmentRequest) {
        Department department = Department.builder().organizationId(departmentRequest.getOrganizationId())
                .name(departmentRequest.getName())
                .employees(null) //TODO add real employees
                .position(departmentRequest.getPosition()).build();
        departmentRepository.save(department);
    }

    public DepartmentResponse getDepartmentById(Long id) {
        return departmentRepository.findById(id).map(this::mapToDepartmentResponse)
                .orElseThrow(() -> new NullPointerException("No department found with id: " + id));

    }

    public List<DepartmentResponse> getDepartments() {
        return departmentRepository.findAll().stream().map(this::mapToDepartmentResponse).toList();
    }

    public DepartmentResponse getDepartmentByOrganizationId(Long organizationId) {
        Department department = departmentRepository.findAll().stream().filter(dep -> dep.getOrganizationId().equals(organizationId))
                .findFirst()
                .orElseThrow(() -> new NullPointerException("No department found with organization id: " + organizationId));

        return mapToDepartmentResponse(department);
    }

    public DepartmentResponse getDepartmentByOrganizationIdWithEmployees(Long organizationId) {
        Department department = departmentRepository.findAll().stream()
                .filter(dep -> dep.getOrganizationId().equals(organizationId))
                .findFirst()
                .orElseThrow(() -> new NullPointerException("No department with employees found with organization id: " + organizationId));

        return mapToDepartmentResponse(department);
    }

    private DepartmentResponse mapToDepartmentResponse(Department department) {
        List<EmployeeResponse> employeeResponses = employeeClient.getAllEmployees();

        List<Employee> employees = employeeResponses.stream().map(this::mapToEmployee)
                .filter(emp -> emp.getOrganizationId().equals(department.getOrganizationId()) &&
                        emp.getDepartmentId().equals(department.getId()))
                .toList();

        return DepartmentResponse.builder()
                .organizationId(department.getOrganizationId())
                .name(department.getName())
                .employees(employees.isEmpty() ? Collections.emptyList() : employees)
                .position(department.getPosition())
                .build();
    }

    private Employee mapToEmployee(EmployeeResponse employeeResponse) {
        System.out.println("Mapping EmployeeResponse: " + employeeResponse);

        return Employee.builder()
                .age(employeeResponse.getAge())
                .name(employeeResponse.getName())
                .position(employeeResponse.getPosition())
                .departmentId(employeeResponse.getDepartmentId())
                .organizationId(employeeResponse.getOrganizationId())
                .build();
    }

}
