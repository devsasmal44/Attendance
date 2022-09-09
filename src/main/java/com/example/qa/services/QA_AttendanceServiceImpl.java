package com.example.qa.services;

import com.example.qa.entity.Attendance;
import com.example.qa.repository.QA_AttendanceRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QA_AttendanceServiceImpl implements QA_AttendanceService {

    private QA_AttendanceRepo attendanceRepo;

    QA_AttendanceServiceImpl(QA_AttendanceRepo attendanceRepo){
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
