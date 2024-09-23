package be.pxl.services.controller;

import be.pxl.services.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/department")
public class OrganizationController {
    private final OrganizationService organizationService;
    /*
    ENDPOINT ORGANIZATION:
@GetMapping("/{id}") findById
@GetMapping("/{id}/with-departments") findByIdWithDepartments
@GetMapping("/{id}/with-departments-and-employees") findByIdWithDepartmentsAndEmployees
@GetMapping("/{id}/with-employees") findByIdWithEmployees
     */

    @GetMapping("/{id}")
    private ResponseEntity<?> getOrganizationById(@PathVariable Long id){
        try {
            return new ResponseEntity<>(organizationService.getOrganizationById(id), HttpStatus.OK);
        }catch (NullPointerException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/with-departments")
    private ResponseEntity<?> getOrganizationByIdWithDepartments(@PathVariable Long id){
        try {
            return new ResponseEntity<>(organizationService.getOrganizationByIdWithDepartments(id), HttpStatus.OK);
        }catch (NullPointerException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/with-departments-and-employees")
    private ResponseEntity<?> getOrganizationByIdWithDepartmentsAndEmployees(@PathVariable Long id){
        try {
            return new ResponseEntity<>(organizationService.getOrganizationByIdWithDepartmentsAndEmployees(id), HttpStatus.OK);
        }catch (NullPointerException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/with-employees")
    private ResponseEntity<?> getOrganizationByIdWithEmployees(@PathVariable Long id){
        try {
            return new ResponseEntity<>(organizationService.getOrganizationByIdWithEmployees(id), HttpStatus.OK);
        }catch (NullPointerException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
