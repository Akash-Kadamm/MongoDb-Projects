package com.mongodb.SpringbootMongodbRestApi.service;

import com.mongodb.SpringbootMongodbRestApi.entity.Employee;
import com.mongodb.SpringbootMongodbRestApi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(long employeeId) {
        Optional<Employee> oldEmployee= employeeRepository.findById(employeeId);
        if (oldEmployee.isEmpty()){
            return null;
        }else {
            return oldEmployee.get();
        }
    }

    public String addEmployee(Employee employee) {
        employeeRepository.save(employee);
        return "Employee added";
    }

    public String UpdateEmployee(long employeeId, Employee employee) {
        Optional<Employee> oldEmployee=employeeRepository.findById(employeeId);
        if (oldEmployee.isEmpty()) {
            return "Failed To Find Employee";
        }else {
            oldEmployee.get().setFirstName(employee.getFirstName());
            oldEmployee.get().setLastName(employee.getLastName());
            employeeRepository.save(oldEmployee.get());
            return "Employee Updated";
        }
    }

    public String DeleteEmployee(long employeeId) {
        Optional<Employee> oldEmployee=employeeRepository.findById(employeeId);
        if (oldEmployee.isEmpty()) {
            return "Failed To Find Employee";
        }else {
            employeeRepository.delete(oldEmployee.get());
            return "Employee Deleted";
        }
    }


}
