package com.mongodb.SpringbootMongodbRestApi.repository;

import com.mongodb.SpringbootMongodbRestApi.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, Long> {

    public Employee findByFirstName(String firstName);
}
