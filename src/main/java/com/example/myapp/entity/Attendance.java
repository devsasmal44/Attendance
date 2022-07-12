package com.example.myapp.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "attendance")
public class Attendance {

    @Id
    private int id;
    private String email;
    private double temperature;
    private LocalDateTime date ;

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public double getTemperature() {
        return temperature;
    }

    public LocalDateTime getDate() {
        return date;
    }

 }
