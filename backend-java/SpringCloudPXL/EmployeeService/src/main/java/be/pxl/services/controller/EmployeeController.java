package be.pxl.services.controller;

import be.pxl.services.domain.dto.EmployeeRequest;
import be.pxl.services.domain.dto.EmployeeResponse;
import be.pxl.services.service.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employee")
public class EmployeeController {
    private final IEmployeeService employeeService;

    @PostMapping
    public ResponseEntity<?> addEmployee(@RequestBody EmployeeRequest employeeRequest){
        try {
            employeeService.addEmployee(employeeRequest);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id){
        try {
            return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
        }catch (NullPointerException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getEmployees(){
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<?> getEmployeeByDepartment(@PathVariable Long departmentId){
        try {
            return new ResponseEntity<>(employeeService.getEmployeeByDepartment(departmentId), HttpStatus.OK);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/organization/{organizationId}")
    public ResponseEntity<?> getEmployeeByOriganization(@PathVariable Long organizationId){
        try {
            return new ResponseEntity<>(employeeService.getEmployeeByOrganization(organizationId), HttpStatus.OK);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
