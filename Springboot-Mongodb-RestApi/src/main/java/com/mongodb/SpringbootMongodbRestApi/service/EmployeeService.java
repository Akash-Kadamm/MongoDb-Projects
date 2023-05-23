package com.mongodb.SpringbootMongodbRestApi.service;

import com.mongodb.SpringbootMongodbRestApi.entity.Employee;
import com.mongodb.SpringbootMongodbRestApi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(long employeeId) {
        return employeeRepository.findById(employeeId).get();
    }

    public String addEmployee(Employee employee) {
        employeeRepository.save(employee);
        return "Employee added";
    }

    public String UpdateEmployee(long employeeId, Employee employee) {
        Employee oldEmployee=employeeRepository.findById(employeeId).get();
        if (oldEmployee == null) {
            return "Failed To Find Employee";
        }else {
            oldEmployee.setFirstName(employee.getFirstName());
            oldEmployee.setLastName(employee.getLastName());
            employeeRepository.save(oldEmployee);
            return "Employee Updated";
        }
    }

    public String DeleteEmployee(long employeeId) {
        Employee oldEmployee=employeeRepository.findById(employeeId).get();
        if (oldEmployee == null) {
            return "Failed To Find Employee";
        }else {
            employeeRepository.delete(oldEmployee);
            return "Employee Deleted";
        }
    }


}
