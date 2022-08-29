package com.example.qa.services;

import com.example.qa.entity.QA_Attendance;

import java.util.List;

public interface QA_AttendanceService {

         QA_Attendance saveAttendance(QA_Attendance attendance);

         List<QA_Attendance> getAttendance();
}
