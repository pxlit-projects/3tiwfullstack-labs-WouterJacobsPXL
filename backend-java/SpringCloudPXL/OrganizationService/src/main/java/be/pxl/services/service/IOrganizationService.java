package be.pxl.services.service;

import be.pxl.services.domain.dto.OrganizationResponse;

public interface IOrganizationService {
    OrganizationResponse getOrganizationById(Long id);

    OrganizationResponse getOrganizationByIdWithDepartments(Long id);

    OrganizationResponse getOrganizationByIdWithDepartmentsAndEmployees(Long id);

    OrganizationResponse getOrganizationByIdWithEmployees(Long id);
}
