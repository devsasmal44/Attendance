package com.example.dev.services;

import com.example.dev.entity.Attendance;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.util.List;

public interface AttendanceService {

         Attendance saveAttendance(Attendance attendance);

         List<Attendance> getAttendance();
}
