package com.example.myapp.services;

import com.example.myapp.entity.Attendance;

import java.util.List;

public interface AttendanceService {

         Attendance saveAttendance(Attendance attendance);

         List<Attendance> getAttendance();

         Attendance updateAttendance(String email, Attendance attendance);

         void deleteAttendance(String email);
}
