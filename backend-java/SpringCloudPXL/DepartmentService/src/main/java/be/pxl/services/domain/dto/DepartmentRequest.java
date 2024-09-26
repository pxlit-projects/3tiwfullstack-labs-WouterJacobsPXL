package be.pxl.services.domain.dto;

import be.pxl.services.domain.Employee;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentRequest {
    private Long organizationId;
    private String name;
    @Transient
    private List<Employee> employees;
    private String position;
}
