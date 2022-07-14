package com.example.myapp.repository;

import com.example.myapp.entity.Attendance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepo extends MongoRepository<Attendance, String> {

}
