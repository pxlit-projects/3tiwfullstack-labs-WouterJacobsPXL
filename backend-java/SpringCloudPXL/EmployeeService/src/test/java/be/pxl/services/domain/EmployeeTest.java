package be.pxl.services.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
public class EmployeeTest {
    @Test
    public void testEmployeeConstructor() {
        Employee employee = new Employee(1L, 101L, 201L, "Proefkonijn", 30, "Junior Tester");

        assertThat(employee.getId()).isEqualTo(1L);
        assertThat(employee.getOrganizationId()).isEqualTo(101L);
        assertThat(employee.getDepartmentId()).isEqualTo(201L);
        assertThat(employee.getName()).isEqualTo("Proefkonijn");
        assertThat(employee.getAge()).isEqualTo(30);
        assertThat(employee.getPosition()).isEqualTo("Junior Tester");
    }

    @Test
    public void testEmployeeBuilder() {
        Employee employee = Employee.builder()
                .id(1L)
                .organizationId(101L)
                .departmentId(201L)
                .name("Proefkonijn")
                .age(30)
                .position("Junior Tester")
                .build();

        assertThat(employee.getId()).isEqualTo(1L);
        assertThat(employee.getOrganizationId()).isEqualTo(101L);
        assertThat(employee.getDepartmentId()).isEqualTo(201L);
        assertThat(employee.getName()).isEqualTo("Proefkonijn");
        assertThat(employee.getAge()).isEqualTo(30);
        assertThat(employee.getPosition()).isEqualTo("Junior Tester");
    }


}
