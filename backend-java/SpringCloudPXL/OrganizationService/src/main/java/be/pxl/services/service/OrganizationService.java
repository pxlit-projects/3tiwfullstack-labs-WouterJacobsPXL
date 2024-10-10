package be.pxl.services.service;

import be.pxl.services.client.EmployeeClient;
import be.pxl.services.domain.Employee;
import be.pxl.services.domain.Organization;
import be.pxl.services.domain.dto.EmployeeResponse;
import be.pxl.services.domain.dto.OrganizationResponse;
import be.pxl.services.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationService implements IOrganizationService{
    private final OrganizationRepository organizationRepository;
    private final EmployeeClient employeeClient;

    public OrganizationResponse getOrganizationById(Long id) {
        return organizationRepository.findById(id).map(this::mapToOrganizationResponse)
                .orElseThrow(() -> new NullPointerException("No organization found with id: " + id));
    }

    public OrganizationResponse getOrganizationByIdWithDepartments(Long id) {
        Organization organization = organizationRepository.findAll().stream()
                .filter(org -> org.getId().equals(id) && !org.getDepartments().isEmpty())
                .findFirst()
                .orElseThrow(() -> new NullPointerException("No department with employees found with organization id: " + id));

        return mapToOrganizationResponse(organization);
    }

    public OrganizationResponse getOrganizationByIdWithDepartmentsAndEmployees(Long id) {
        Organization organization = organizationRepository.findAll().stream()
                .filter(org -> org.getId().equals(id) && !org.getDepartments().isEmpty() && !org.getEmployees().isEmpty())
                .findFirst()
                .orElseThrow(() -> new NullPointerException("No department with employees found with organization id: " + id));

        return mapToOrganizationResponse(organization);    }

    public OrganizationResponse getOrganizationByIdWithEmployees(Long id) {
        Organization organization = organizationRepository.findAll().stream()
                .filter(org -> org.getId().equals(id) && !org.getEmployees().isEmpty())
                .findFirst()
                .orElseThrow(() -> new NullPointerException("No department with employees found with organization id: " + id));

        return mapToOrganizationResponse(organization);    }

    private OrganizationResponse mapToOrganizationResponse(Organization organization) {
        List<Employee> employees = employeeClient.getAllEmployees().stream().map(this::mapToEmployee).toList();

        return OrganizationResponse.builder().name(organization.getName())
                .address(organization.getAddress())
                .employees(employees)                //TODO fill with real employees !maybe!
                .departments(null).build();     //TODO fill with real departments
    }

    private Employee mapToEmployee(EmployeeResponse employeeResponse) {
        return Employee.builder().age(employeeResponse.getAge())
                .name(employeeResponse.getName())
                .position(employeeResponse.getPosition())
                .departmentId(employeeResponse.getDepartmentId())
                .organizationId(employeeResponse.getOrganizationId())
                .build();
    }
}
