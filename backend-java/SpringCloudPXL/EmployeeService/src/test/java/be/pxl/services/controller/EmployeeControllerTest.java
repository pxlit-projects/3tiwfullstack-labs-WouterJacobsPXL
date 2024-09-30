package be.pxl.services.controller;

import be.pxl.services.domain.Employee;
import be.pxl.services.repository.EmployeeRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EmployeeRepository employeeRepository;
    private static int employeeIdCounter;
    @Container
    private static final MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.0");

    @DynamicPropertySource
    static void registerMySQLProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url",mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username",mySQLContainer::getUsername);
        registry.add("spring.datasource.password",mySQLContainer::getPassword);
    }

    @BeforeEach
    public void init(){
        clearDatabase();
    }

    private void clearDatabase() {
        employeeRepository.deleteAll();
    }

    private void fillDatabaseWithMockData(){
        List<Employee> employees = List.of(
                Employee.builder().name("Jan").age(24).position("Developer").departmentId(100L).organizationId(1L).build(),
                Employee.builder().name("Tom").age(24).position("Tester").departmentId(100L).organizationId(2L).build(),
                Employee.builder().name("Kris").age(24).position("Manager").departmentId(101L).organizationId(2L).build(),
                Employee.builder().name("Lowie").age(24).position("CEO").departmentId(101L).organizationId(3L).build(),
                Employee.builder().name("Eva").age(24).position("Secretary").departmentId(102L).organizationId(7L).build()
        );
        for (Employee e:
                employees) {
            employeeRepository.save(e);
            incrementEmployeeIdCounter();
        }
    }

    private void incrementEmployeeIdCounter(){
        employeeIdCounter++;
    }

    @Test
    public void testAddValidEmployeeReturnsIsCreated() throws Exception {
        Employee employee = Employee.builder()
                .name("Jan")
                .age(35)
                .position("Developer")
                .organizationId(101L)
                .departmentId(201L)
                .build();

        String employeeString = objectMapper.writeValueAsString(employee);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(employeeString))
                .andExpect(status().isCreated());
        incrementEmployeeIdCounter();

        assertEquals(1, employeeRepository.findAll().size());
    }

    @Test
    public void testAddInvalidEmployeeReturnsBadRequest() throws Exception {
        String invalidEmployeeString =
                "{\"id\":null,\"organizationId\":null,\"departmentId\":null,\"name\":\"" +
                        "10\",\"age\":-5,\"position\":\"freebooter\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidEmployeeString))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid employee data"));

        assertEquals(0, employeeRepository.findAll().size());
    }

    @Test
    public void testGetEmployeeByIdReturnsCorrectEmployee() throws Exception {
        Employee employee = Employee.builder()
                .name("Tester Jan")
                .age(24)
                .position("Developer")
                .build();

        employeeRepository.save(employee);
        incrementEmployeeIdCounter();

        String url = "/api/employee/%d".formatted(employeeIdCounter);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Employee calledEmployee = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),Employee.class);

        assertEquals("Tester Jan", calledEmployee.getName());
        assertEquals(24, calledEmployee.getAge());
        assertEquals("Developer", calledEmployee.getPosition());
    }

    @Test
    public void testGetEmployeeByIdWithUnknownIdReturnsNotFound() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/employee/-1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        assertEquals("No employee found with id: -1", mvcResult.getResponse().getContentAsString());

    }

    @Test
    public void testGetEmployeesReturnsAllEmployees() throws Exception {
        fillDatabaseWithMockData();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/employee")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        List<Employee> employeesResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>(){});

        assertEquals("Jan", employeesResponse.getFirst().getName());
        assertEquals("Developer", employeesResponse.getFirst().getPosition());

        assertEquals("Kris", employeesResponse.get(2).getName());
        assertEquals("Manager", employeesResponse.get(2).getPosition());

        assertEquals("Eva", employeesResponse.getLast().getName());
        assertEquals("Secretary", employeesResponse.getLast().getPosition());
    }

    @Test
    public void testGetEmployeesWithNoEmployeesInDatabaseReturnsNotFound() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/employee")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        assertEquals("No employees found", mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testGetEmployeesByDepartmentIdReturnsCorrectEmployees() throws Exception {
        fillDatabaseWithMockData();
        String url = "/api/employee/department/%d".formatted(101L);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        List<Employee> employeesResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>(){});

        assertEquals(2, employeesResponse.size());
        assertEquals("Kris", employeesResponse.getFirst().getName());
        assertEquals("Manager", employeesResponse.getFirst().getPosition());
    }

    @Test
    public void testGetEmployeesByDepartmentIdWithNoEmployeesWithRequestedDepartmentIdInDatabaseReturnsNotFound() throws Exception {
        String url = "/api/employee/department/%d".formatted(-1L);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        assertEquals("No employees found with department id: -1", mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testGetEmployeeByOrganizationIdReturnsCorrectEmployees() throws Exception {
        fillDatabaseWithMockData();
        String url = "/api/employee/organization/%d".formatted(2L);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        List<Employee> employeesResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>(){});

        assertEquals(2, employeesResponse.size());
        assertEquals("Tom", employeesResponse.getFirst().getName());
        assertEquals("Tester", employeesResponse.getFirst().getPosition());
    }

    @Test
    public void testGetEmployeeByOrganizationIdtWithNoEmployeesWithRequestedOrganizationIdInDatabaseReturnsNotFound() throws Exception {
        String url = "/api/employee/organization/%d".formatted(-1L);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        assertEquals("No employees found with organization id: -1", mvcResult.getResponse().getContentAsString());
    }
}
