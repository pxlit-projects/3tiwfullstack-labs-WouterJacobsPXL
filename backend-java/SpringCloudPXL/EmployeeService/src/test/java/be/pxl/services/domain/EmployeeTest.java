package be.pxl.services.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
public class EmployeeTest {
    @Container
    private static final MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.0");

    @DynamicPropertySource
    static void registerMySQLProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url",mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username",mySQLContainer::getUsername);
        registry.add("spring.datasource.password",mySQLContainer::getPassword);
    }

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
