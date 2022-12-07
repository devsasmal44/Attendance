package com.example.qa.controller;

import com.example.qa.entity.Attendance;
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
import java.util.*;
import java.util.stream.Collectors;

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
    public void saveAttendance(@RequestBody Attendance attendance) {
        attendance.setTimestamp(Instant.now().getEpochSecond());
        attendance.setLocation(attendance.getLocation());
        attendance.setDates(String.valueOf(LocalDate.now()));
        attendanceService.saveAttendance(attendance);
    }

    @GetMapping("/qa/list")
    public List<Attendance> getAttendance() {
        return attendanceService.getAttendance();
    }

    ///names?long=77.63&lat=12.91
    @GetMapping("/qa/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        String  todaysDate = String.valueOf(LocalDate.now());
        String beforeTwoWeekDate = String.valueOf(LocalDate.parse(String.valueOf(todaysDate)).minusDays(6));
        Query query = new Query(Criteria.where("dates").gte(beforeTwoWeekDate).lte(todaysDate));
        List<Attendance> attendanceList = mongoOperations.find(query, Attendance.class);
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headervalue = "attachment; filename=Attendance List.xlsx";

        response.setHeader(headerKey, headervalue);
        QA_AttendanceExcelExporter exp = new QA_AttendanceExcelExporter(attendanceList);
        exp.export(response);

    }

    @GetMapping("/nameList")
    public List<String> fetchNames(@RequestParam(name ="lat") String latitude,@RequestParam(name = "long") String longitude){
        Attendance attendance = new Attendance(Double.parseDouble(latitude), Double.parseDouble(longitude));
        String officeBranch = attendance.getOfficeBranch();

        Query query = new Query();
        List<Criteria> criteria = new ArrayList<>();
        String presentDate = String.valueOf(LocalDate.now());
        criteria.add(Criteria.where("dates").is(presentDate));

        if(officeBranch == "Bangalore"){
            String locationCheck = "Bangalore";
            criteria.add(Criteria.where("location").is(locationCheck));
        }
        else if(officeBranch == "Hyderabad"){
            String locationCheck = "Hyderabad";
            criteria.add(Criteria.where("location").is(locationCheck));
        }
        else if(officeBranch == "Pune"){
            String locationCheck = "Pune";
            criteria.add(Criteria.where("location").is(locationCheck));
        } else {
            return null;
        }
        query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
        List<Attendance> attendanceList = mongoOperations.find(query, Attendance.class);

        List<String> nameList = new ArrayList<>();
        for(Attendance a : attendanceList ){
            nameList.add(a.getName());
        }
        List<String> filteredNameList = nameList.stream().distinct().collect(Collectors.toList());

        try {
            Collections.sort(filteredNameList);
        }
        catch (Exception e) {
            System.out.printf("sorting with null value :"+e.getMessage());
        }
        return filteredNameList;
    }
}