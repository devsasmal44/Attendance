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
import java.util.*;

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
    public List<String> nameList(){

        QA_Attendance qa_attendance = new QA_Attendance();
        qa_attendance.setLocation(qa_attendance.getLocation());
        String loc = qa_attendance.location_check();

        Query queryOne = new Query();
        Query queryTwo = new Query();
        Query queryThree = new Query();
        List<Criteria> criteriaOne = new ArrayList<>();
        List<Criteria> criteriaTwo = new ArrayList<>();
        List<Criteria> criteriaThree = new ArrayList<>();
        String datesCheck = String.valueOf(LocalDate.now());

        criteriaOne.add(Criteria.where("dates").is(datesCheck));
        String locationOne = "Bangalore";
        criteriaOne.add(Criteria.where("location").is(locationOne));
        queryOne.addCriteria(new Criteria().andOperator(criteriaOne.toArray(new Criteria[criteriaOne.size()])));
        List<QA_Attendance> BlrNameList = mongoOperations.find(queryOne, QA_Attendance.class);

        criteriaTwo.add(Criteria.where("dates").is(datesCheck));
        String locationTwo = "Hyderabad";
        criteriaTwo.add(Criteria.where("location").is(locationTwo));
        queryTwo.addCriteria(new Criteria().andOperator(criteriaTwo.toArray(new Criteria[criteriaTwo.size()])));
        List<QA_Attendance> HydNameList = mongoOperations.find(queryTwo, QA_Attendance.class);

        criteriaThree.add(Criteria.where("dates").is(datesCheck));
        String locationThree = "Pune";
        criteriaThree.add(Criteria.where("location").is(locationThree));
        queryThree.addCriteria(new Criteria().andOperator(criteriaThree.toArray(new Criteria[criteriaThree.size()])));
        List<QA_Attendance> PuneNameList = mongoOperations.find(queryThree, QA_Attendance.class);

        List<String> NameListOne = new ArrayList<>();
        List<String> NameListTwo = new ArrayList<>();
        List<String> NameListThree = new ArrayList<>();
        for(QA_Attendance a : BlrNameList ){
            NameListOne.add(a.getName());
        }
        Collections.sort(NameListOne);

        for(QA_Attendance a : HydNameList ){
            NameListTwo.add(a.getName());
        }
        Collections.sort(NameListTwo);

        for(QA_Attendance a : PuneNameList ){
            NameListThree.add(a.getName());
        }
        Collections.sort(NameListThree);

        if(loc == "Bangalore"){
            return NameListOne;
        }
        else if(loc == "Hyderabad"){
            return NameListTwo;
        }
        else if(loc == "Pune"){
            return NameListThree;
        } else {
            return null;
        }
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
