package com.example.dev.services;

import com.example.dev.repository.AttendanceRepo;
import com.example.dev.entity.Attendance;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private AttendanceRepo attendanceRepo;

    AttendanceServiceImpl(AttendanceRepo attendanceRepo){
        this.attendanceRepo=attendanceRepo;
    }

    @Override
    public Attendance saveAttendance(Attendance attendance) {
        return attendanceRepo.save(attendance);
    }

    @Override
    public List<Attendance> getAttendance() {
        return attendanceRepo.findAll();
    }

}
