package com.example.qa.services;

import com.example.qa.entity.Attendance;

import java.util.List;

public interface QA_AttendanceService {

         Attendance saveAttendance(Attendance attendance);

         List<Attendance> getAttendance();
}
