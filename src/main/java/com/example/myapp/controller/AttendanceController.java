package com.example.myapp.controller;

import com.example.myapp.entity.Attendance;
import com.example.myapp.excel.AttendanceExcelExporter;
import com.example.myapp.repository.AttendanceRepo;
import com.example.myapp.services.AttendanceService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    private AttendanceService attendanceService;
    private AttendanceRepo attendanceRepo;

    AttendanceController(AttendanceService attendanceService, AttendanceRepo attendanceRepo){
        this.attendanceService =attendanceService;
        this.attendanceRepo = attendanceRepo;
    }

    @PostMapping("/save")
    public Attendance saveAttendance(@RequestBody Attendance attendance) {
        attendance.setDate( LocalDateTime.now());
        return attendanceService.saveAttendance(attendance);
    }

    @GetMapping("/list")
    public List<Attendance> getAttendance() {
        return attendanceService.getAttendance();
    }

    @PutMapping("/update/{attendance_email}")
    public Attendance updateAttendance(@RequestBody Attendance employee, @PathVariable("attendance_email") String email) {
        return attendanceService.updateAttendance(email, employee);
    }

    @DeleteMapping("/delete/{attendance_email}")
    public String deleteAttendance(@PathVariable("attendance_email") String email) {
        attendanceService.deleteAttendance(email);
        return "deleted succesfully.";
    }

    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headervalue = "attachment; filename=Employee_info.xlsx";

        response.setHeader(headerKey, headervalue);
        List<Attendance> attendanceList = attendanceRepo.findAll();
        AttendanceExcelExporter exp = new AttendanceExcelExporter(attendanceList);
        exp.export(response);

    }
}
