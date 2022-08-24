package com.example.myapp.controller;

import com.example.myapp.entity.Attendance;
import com.example.myapp.excel.AttendanceExcelExporter;
import com.example.myapp.repository.AttendanceRepo;
import com.example.myapp.services.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/attendance")
@CrossOrigin(origins = {"http://localhost:8000", "http://localhost:8000/"})
public class AttendanceController {

    private AttendanceService attendanceService;
    private AttendanceRepo attendanceRepo;

    @Autowired
    MongoOperations mongoOperations;
    AttendanceController(AttendanceService attendanceService, AttendanceRepo attendanceRepo){
        this.attendanceService =attendanceService;
        this.attendanceRepo = attendanceRepo;
    }
    @CrossOrigin
    @PostMapping("/save")
    public void saveAttendance(@RequestBody Attendance attendance) {
        attendance.setTimestamp(Instant.now().getEpochSecond());
        attendance.setLocation(attendance.getLocation());
        attendanceService.saveAttendance(attendance);
    }


    @GetMapping("/list")
    public List<Attendance> getAttendance() {
        return attendanceService.getAttendance();
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
