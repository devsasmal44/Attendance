package com.example.qa.repository;

import com.example.qa.entity.QA_Attendance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QA_AttendanceRepo extends MongoRepository<QA_Attendance, String> {

}
