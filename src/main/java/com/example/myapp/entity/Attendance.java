package com.example.myapp.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "attendance")
public class Attendance {

    private String email;
    private double temperature;
    private String latitude;
    private String longitude;
    private LocalDateTime date ;


    public void setEmail(String email) {
        this.email = email;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public LocalDateTime getDate() {
        return date;
    }

 }
