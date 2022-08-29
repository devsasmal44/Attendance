package com.example.dev.repository;

import com.example.dev.entity.Attendance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepo extends MongoRepository<Attendance, String> {

}
