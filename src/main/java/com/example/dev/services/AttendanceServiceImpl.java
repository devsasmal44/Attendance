package com.example.dev.services;

import com.example.dev.excel.AttendanceExcelExporter;
import com.example.dev.repository.AttendanceRepo;
import com.example.dev.entity.Attendance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private AttendanceRepo attendanceRepo;

    @Autowired
    MongoOperations mongoOperations;

    AttendanceServiceImpl(AttendanceRepo attendanceRepo){
        this.attendanceRepo=attendanceRepo;
    }

    @Override
    public Attendance saveAttendance(Attendance attendance) {
        return attendanceRepo.save(attendance);
    }

    @Override
    public List<Attendance> getAttendance() {
        return attendanceRepo.findAll();
    }

    @Override
    public ByteArrayInputStream load()  {
        String  todaysDate = String.valueOf(LocalDate.now());
        String beforeTwoWeeksDate = String.valueOf(LocalDate.parse(String.valueOf(todaysDate)).minusWeeks(2));
        Query query = new Query(Criteria.where("dates").gte(beforeTwoWeeksDate).lte(todaysDate));
        List<Attendance> attendanceList = mongoOperations.find(query, Attendance.class);

        ByteArrayInputStream in = AttendanceExcelExporter.writeDataLines(attendanceList);
        return in;
    }

}
