package be.pxl.services.controller;

import be.pxl.services.service.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employee")
public class EmployeeController {
    private final IEmployeeService employeeService;

    @GetMapping
    public ResponseEntity getEmployees(){
        return new ResponseEntity(employeeService.getAllEmployees(), HttpStatus.OK);
    }
}
