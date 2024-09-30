package be.pxl.services.service;

import be.pxl.services.domain.dto.EmployeeRequest;
import be.pxl.services.domain.dto.EmployeeResponse;

import java.util.List;

public interface IEmployeeService {
    void addEmployee(EmployeeRequest employeeRequest);
    List<EmployeeResponse> getAllEmployees();
    EmployeeResponse getEmployeeById(Long id);
    List<EmployeeResponse> getEmployeesByDepartment(Long departmentId);
    List<EmployeeResponse> getEmployeesByOrganization(Long organizationId);
}
