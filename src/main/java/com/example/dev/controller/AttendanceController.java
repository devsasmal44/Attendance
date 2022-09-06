package com.example.dev.controller;

import com.example.dev.entity.Attendance;
import com.example.dev.excel.AttendanceExcelExporter;
import com.example.dev.repository.AttendanceRepo;
import com.example.dev.services.AttendanceService;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@RestController
@RequestMapping("/attendance")
@CrossOrigin
public class AttendanceController {

    private AttendanceService attendanceService;
    private AttendanceRepo attendanceRepo;

    @Autowired
    MongoOperations mongoOperations;

    AttendanceController(AttendanceService attendanceService, AttendanceRepo attendanceRepo){
        this.attendanceService =attendanceService;
        this.attendanceRepo = attendanceRepo;
    }

    @PostMapping("/save")
    public void saveAttendance(@RequestBody Attendance attendance) {
        attendance.setTimestamp(Instant.now().getEpochSecond());
        attendance.setLocation(attendance.getLocation());
        attendance.setDates(String.valueOf(LocalDate.now()));
        attendanceService.saveAttendance(attendance);
    }
    @GetMapping("/list")
    public List<Attendance> getAttendance() {
        return attendanceService.getAttendance();
    }

    @GetMapping("/nameList")
    public String nameList(){
        String datesCheck = String.valueOf(LocalDate.now());
        Query query = new Query(Criteria.where("dates").is(datesCheck));
        List<Attendance> attendanceList = mongoOperations.find(query, Attendance.class);
        List<String> nameList = new ArrayList<>();
        for(Attendance a : attendanceList ){
            nameList.add(a.getName());
        }
        String delim = " ";
        StringJoiner joiner = new StringJoiner(delim);
        for (String s : nameList) {
            joiner.add(s.toString());
        }
        String res = joiner.toString()+ "\n";
        return res;
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
