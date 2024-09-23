package be.pxl.services.service;

import be.pxl.services.domain.dto.DepartmentRequest;
import be.pxl.services.domain.dto.DepartmentResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IDepartmentService {
    void addDepartment(DepartmentRequest departmentRequest);
    DepartmentResponse getDepartmentById(Long id);
    List<DepartmentResponse> getDepartments();
    DepartmentResponse getDepartmentByOrganizationId(Long organizationId);
    DepartmentResponse getDepartmentByOrganizationIdWithEmployees(Long organizationId);

}
