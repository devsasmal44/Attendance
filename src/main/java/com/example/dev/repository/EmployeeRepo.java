package com.example.dev.repository;


import com.example.dev.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends MongoRepository<Employee, String> {

}