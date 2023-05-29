package com.mongodb.SpringbootMongodbRestApi.service;

import com.mongodb.SpringbootMongodbRestApi.entity.Employee;
import com.mongodb.SpringbootMongodbRestApi.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private MongoTemplate mongoTemplate;


    private Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(long employeeId) {
        Optional<Employee> oldEmployee = employeeRepository.findById(employeeId);
        if (oldEmployee.isEmpty()) {
            return null;
        } else {
            return oldEmployee.get();
        }
    }

    public String addEmployee(Employee employee) {
        employeeRepository.save(employee);
        Query query = new Query();
        query.addCriteria(Criteria.where("Id").lt(2));
        List<Employee> employees = mongoTemplate.find(query, Employee.class);
        for (Employee employeeList : employees) {
            logger.info("Employee is:" + employeeList.toString());
        }
        return "Employee added";
    }

    public String UpdateEmployee(long employeeId, Employee employee) {
        Optional<Employee> oldEmployee = employeeRepository.findById(employeeId);
        if (oldEmployee.isEmpty()) {
            return "Failed To Find Employee";
        } else {
            oldEmployee.get().setFirstName(employee.getFirstName());
            oldEmployee.get().setLastName(employee.getLastName());
            employeeRepository.save(oldEmployee.get());
            return "Employee Updated";
        }
    }

    public String DeleteEmployee(long employeeId) {
        Optional<Employee> oldEmployee = employeeRepository.findById(employeeId);
        if (oldEmployee.isEmpty()) {
            return "Failed To Find Employee";
        } else {
            employeeRepository.delete(oldEmployee.get());
            return "Employee Deleted";
        }
    }

    public Employee getEmployeeByFirstName(String firstName) {
        return employeeRepository.findByFirstName(firstName);
    }


}
