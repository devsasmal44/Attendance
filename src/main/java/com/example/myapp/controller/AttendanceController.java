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
        attendance.setDates(String.valueOf(LocalDate.now()));
        attendance.setLocation("Out of office.");
        attendanceService.saveAttendance(attendance);
    }

    @GetMapping("/list")
    public List<Attendance> getAttendance() {
        return attendanceService.getAttendance();
    }

    public void insertLocation(List<Attendance> attendanceLists, Query query){
        Update update = new Update();
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        double bangaloreLatitude = 12.91;
        double bangaloreLongitude = 77.63;

        double hyderabadLatitude = 17.42;
        double hyderabadLongitude = 78.33;

        double puneLatitude = 18.53;
        double puneLongitude = 73.87;

        for(Attendance attendance : attendanceLists) {
            double latitudeCheck = Double.parseDouble(df.format(attendance.getLatitude()));
            System.out.println(latitudeCheck);
            double longitudeCheck = Double.parseDouble(df.format(attendance.getLongitude()));
            if(latitudeCheck == bangaloreLatitude && longitudeCheck == bangaloreLongitude) {
                attendance.setLocation("Bangalore");
                update.set("location", attendance.getLocation());
                mongoOperations.findAndModify(query, update, Attendance.class);
            } else if (latitudeCheck == hyderabadLatitude && longitudeCheck == hyderabadLongitude) {
                attendance.setLocation("Hyderabad");
                update.set("location", attendance.getLocation());
                mongoOperations.findAndModify(query, update, Attendance.class);
            } else if (latitudeCheck == puneLatitude && longitudeCheck == puneLongitude){
                attendance.setLocation("Pune");
                update.set("location", attendance.getLocation());
                mongoOperations.findAndModify(query, update, Attendance.class);
            } else {
                attendance.setLocation("Out of Office");
                update.set("location", attendance.getLocation());
                mongoOperations.findAndModify(query, update, Attendance.class);
            }
        }
    }

    @GetMapping("/checkLocation")
    public void checkLocation(){
        String emailCheck = "lmn@gmail.com";
        Query query = new Query(Criteria.where("email").is(emailCheck));
        List<Attendance> attendanceList = mongoOperations.find(query, Attendance.class);
        insertLocation(attendanceList, query);
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
