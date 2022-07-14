package com.example.myapp.services;

import com.example.myapp.repository.AttendanceRepo;
import com.example.myapp.entity.Attendance;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private AttendanceRepo attendanceRepo;

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
    public Attendance updateAttendance(String email, Attendance attendance) {
        Optional<Attendance> findById = attendanceRepo.findById(email);
        if (findById.isPresent()) {
            Attendance attendanceEntity = findById.get();
            if (attendance.getEmail() != null && !attendance.getEmail().isEmpty())
                attendanceEntity.setEmail(attendance.getEmail());
            return attendanceRepo.save(attendanceEntity);
        }
        return null;
    }

    @Override
    public void deleteAttendance(String email) {
        attendanceRepo.deleteById(email);
    }
}
