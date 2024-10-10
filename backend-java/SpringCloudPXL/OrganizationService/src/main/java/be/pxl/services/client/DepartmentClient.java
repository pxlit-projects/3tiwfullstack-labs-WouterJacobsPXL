package be.pxl.services.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "department-service")
public interface DepartmentClient {
}
