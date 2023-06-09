package com.mongodb.SpringbootMongodbRestApi.controller;

import com.mongodb.SpringbootMongodbRestApi.entity.Employee;
import com.mongodb.SpringbootMongodbRestApi.exception.ResourceNotFoundException;
import com.mongodb.SpringbootMongodbRestApi.repository.EmployeeRepository;
import com.mongodb.SpringbootMongodbRestApi.service.EmployeeService;
import com.mongodb.SpringbootMongodbRestApi.service.SequenceGeneratorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@Tag(
        name = "Employee Management System",
        description = "The system"
)
@CrossOrigin("*")
@RequestMapping(value = "/api/v1")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;


    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @Operation(
            summary ="Create New Employee resource",
            description = "Create a new employee and save into mongodb database"
    )
    @ApiResponse(
            description = "Create Employee resource return 201 CREATED",
            responseCode = "201"
    )
    @PostMapping("/employees")
    public ResponseEntity<String> createEmployee(@Valid @RequestBody Employee employee) {
        employee.setId(sequenceGeneratorService.generateSequence(Employee.SEQUENCE_NAME));
        return new ResponseEntity<>(employeeService.addEmployee(employee), HttpStatus.CREATED);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long employeeId) {
        return new ResponseEntity<>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
    }

    @PutMapping("/updateEmployee/{employeeId}")
    public ResponseEntity<String> updateEmployees(@PathVariable long employeeId, @RequestBody @Valid Employee employee) {
        return new ResponseEntity<>(employeeService.UpdateEmployee(employeeId, employee), HttpStatus.OK);
    }

    @DeleteMapping("/deleteEmployee/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable long employeeId) {
        return new ResponseEntity<>(employeeService.DeleteEmployee(employeeId), HttpStatus.OK);
    }


    @GetMapping("/getByName/{employeeName}")
    public ResponseEntity<Employee> getEmployeeByName(@PathVariable String employeeName) {
        return new ResponseEntity<>(employeeService.getEmployeeByFirstName(employeeName), HttpStatus.OK);
    }

}
