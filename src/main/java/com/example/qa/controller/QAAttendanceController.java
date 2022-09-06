package com.example.qa.controller;

import com.example.dev.entity.Attendance;
import com.example.qa.entity.QA_Attendance;
import com.example.qa.excel.QA_AttendanceExcelExporter;
import com.example.qa.repository.QA_AttendanceRepo;
import com.example.qa.services.QA_AttendanceService;
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
@RequestMapping("/attendance_qa")
@CrossOrigin
public class QAAttendanceController {

    private QA_AttendanceService attendanceService;
    private QA_AttendanceRepo attendanceRepo;

    @Autowired
    MongoOperations mongoOperations;

    QAAttendanceController(QA_AttendanceService attendanceService, QA_AttendanceRepo attendanceRepo){
        this.attendanceService =attendanceService;
        this.attendanceRepo = attendanceRepo;
    }


    @PostMapping("/qa/save")
    public void saveAttendance(@RequestBody QA_Attendance attendance) {
        attendance.setTimestamp(Instant.now().getEpochSecond());
        attendance.setLocation(attendance.getLocation());
        attendance.setDates(String.valueOf(LocalDate.now()));
        attendanceService.saveAttendance(attendance);
    }

    @GetMapping("/qa/list")
    public List<QA_Attendance> getAttendance() {
        return attendanceService.getAttendance();
    }

    @GetMapping("/nameList")
    public String nameList(){
        String datesCheck = String.valueOf(LocalDate.now());
        Query query = new Query(Criteria.where("dates").is(datesCheck));
        List<QA_Attendance> attendanceList = mongoOperations.find(query, QA_Attendance.class);
        List<String> nameList = new ArrayList<>();
        for(QA_Attendance a : attendanceList ){
            nameList.add(a.getName());
        }
        String delim = " ";
        StringJoiner joiner = new StringJoiner(delim);
        for (String s : nameList) {
            joiner.add("\n" + s.toString());
        }
        String res = joiner.toString();
        return res;
    }

    @GetMapping("/qa/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headervalue = "attachment; filename=Employee_info.xlsx";

        response.setHeader(headerKey, headervalue);
        List<QA_Attendance> attendanceList = attendanceRepo.findAll();
        QA_AttendanceExcelExporter exp = new QA_AttendanceExcelExporter(attendanceList);
        exp.export(response);

    }
}
