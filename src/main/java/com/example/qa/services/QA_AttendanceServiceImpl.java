package com.example.qa.services;

import com.example.qa.entity.QA_Attendance;
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
    public QA_Attendance saveAttendance(QA_Attendance attendance) {
        return attendanceRepo.save(attendance);
    }

    @Override
    public List<QA_Attendance> getAttendance() {
        return attendanceRepo.findAll();
    }

}
