package com.example.qa.repository;


import com.example.qa.entity.QA_Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QA_EmployeeRepo extends MongoRepository<QA_Employee, String> {

}