package be.pxl.services.service;

import be.pxl.services.domain.Organization;
import be.pxl.services.domain.dto.OrganizationResponse;
import be.pxl.services.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizationService implements IOrganizationService{
    private final OrganizationRepository organizationRepository;

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
        return OrganizationResponse.builder().name(organization.getName())
                .address(organization.getAddress())
                .employees(null)                //TODO fill with real employees
                .departments(null).build();     //TODO fill with real departments
    }
}
