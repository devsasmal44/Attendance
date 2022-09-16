package com.example.dev.controller;

import com.example.dev.entity.Attendance;
import com.example.dev.excel.AttendanceExcelExporter;
import com.example.dev.repository.AttendanceRepo;
import com.example.dev.services.AttendanceService;
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
        try {
            Collections.sort(nameList);
        }
        catch (Exception e) {
            System.out.printf("sorting with null value :"+e.getMessage());
        }

//        int count = nameList.size();
//        String temp;
//        Object[] name = nameList.toArray();
//        for (int i = 0; i < count; i++) {
//            for (int j = i + 1; j < count; j++) {
//                if (((String) name[i]).compareTo((String) name[j]) > 0) {
//                    temp = (String) name[i];
//                    name[i] = name[j];
//                    name[j] = temp;
//                }
//            }
//        }
//        System.out.println("Sorted Arraylist:");
//        for (int a = 0; a < name.length; a++) {
//            System.out.println(name[a]);
//        }
        return nameList;

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
