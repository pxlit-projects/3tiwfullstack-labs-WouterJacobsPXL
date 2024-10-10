package be.pxl.services.service;

import be.pxl.services.client.DepartmentClient;
import be.pxl.services.client.EmployeeClient;
import be.pxl.services.domain.Department;
import be.pxl.services.domain.Employee;
import be.pxl.services.domain.Organization;
import be.pxl.services.domain.dto.DepartmentResponse;
import be.pxl.services.domain.dto.EmployeeResponse;
import be.pxl.services.domain.dto.OrganizationResponse;
import be.pxl.services.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationService implements IOrganizationService{
    private final OrganizationRepository organizationRepository;
    private final EmployeeClient employeeClient;
    private final DepartmentClient departmentClient;

    public OrganizationResponse getOrganizationById(Long id) {
        return organizationRepository.findById(id).map(organization -> mapToOrganizationResponse(organization, null,null))
                .orElseThrow(() -> new NullPointerException("No organization found with id: " + id));
    }

    public OrganizationResponse getOrganizationByIdWithDepartments(Long id) {
        Organization organization = organizationRepository.findAll().stream()
                .filter(org -> org.getId().equals(id) && !org.getDepartments().isEmpty())
                .findFirst()
                .orElseThrow(() -> new NullPointerException("No department with employees found with organization id: " + id));

        List<Department> departments = departmentClient.getAllDepartments().stream()
                .filter(departmentResponse -> departmentResponse.getOrganizationId().equals(organization.getId()))
                .map(this::mapToDepartment).toList();

        return mapToOrganizationResponse(organization, null , departments);
    }

    public OrganizationResponse getOrganizationByIdWithDepartmentsAndEmployees(Long id) {
        Organization organization = organizationRepository.findAll().stream()
                .filter(org -> org.getId().equals(id) && !org.getDepartments().isEmpty() && !org.getEmployees().isEmpty())
                .findFirst()
                .orElseThrow(() -> new NullPointerException("No department with employees found with organization id: " + id));

        List<Department> departments = departmentClient.getAllDepartments().stream()
                .filter(departmentResponse -> departmentResponse.getOrganizationId().equals(organization.getId()))
                .map(this::mapToDepartment).toList();

        List<Employee> employees = employeeClient.getAllEmployees().stream()
                .filter(employeeResponse -> employeeResponse.getOrganizationId().equals(organization.getId()))
                .map(this::mapToEmployee).toList();

        return mapToOrganizationResponse(organization, employees , departments);
    }

    public OrganizationResponse getOrganizationByIdWithEmployees(Long id) {
        Organization organization = organizationRepository.findAll().stream()
                .filter(org -> org.getId().equals(id) && !org.getEmployees().isEmpty())
                .findFirst()
                .orElseThrow(() -> new NullPointerException("No department with employees found with organization id: " + id));

        List<Employee> employees = employeeClient.getAllEmployees().stream()
                .filter(employeeResponse -> employeeResponse.getOrganizationId().equals(organization.getId()))
                .map(this::mapToEmployee).toList();

        return mapToOrganizationResponse(organization, employees, null);
    }

    private OrganizationResponse mapToOrganizationResponse(Organization organization,
                                                           List<Employee> employees, List<Department> departments) {

        return OrganizationResponse.builder().name(organization.getName())
                .address(organization.getAddress())
                .employees(employees)
                .departments(departments).build();
    }

    private Employee mapToEmployee(EmployeeResponse employeeResponse) {
        return Employee.builder().age(employeeResponse.getAge())
                .name(employeeResponse.getName())
                .position(employeeResponse.getPosition())
                .departmentId(employeeResponse.getDepartmentId())
                .organizationId(employeeResponse.getOrganizationId())
                .build();
    }

    private Department mapToDepartment(DepartmentResponse departmentResponse){
        return Department.builder()
                .organizationId(departmentResponse.getOrganizationId())
                .name(departmentResponse.getName())
                .employees(null)
                .position(departmentResponse.getPosition())
                .build();
    }
}

