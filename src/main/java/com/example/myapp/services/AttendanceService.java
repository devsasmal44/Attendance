package com.example.myapp.services;

import com.example.myapp.entity.Attendance;

import java.util.List;

public interface AttendanceService {

         Attendance saveAttendance(Attendance attendance);

         List<Attendance> getAttendance();

         Attendance updateAttendance(int id, Attendance attendance);

         void deleteAttendance(int id);
}
