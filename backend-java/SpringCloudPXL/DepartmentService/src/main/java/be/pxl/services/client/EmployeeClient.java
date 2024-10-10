package be.pxl.services.client;

import be.pxl.services.domain.dto.EmployeeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "employee-service")

public interface EmployeeClient {
    @GetMapping("/api/employee")
    List<EmployeeResponse> getAllEmployees();
}
