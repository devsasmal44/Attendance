package com.example.myapp.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Document(collection = "attendance")
public class Attendance {

    private String email;
    private float temperature;
    private double latitude;
    private double longitude;
    private String location;
    private long timestamp;
    private String dates;
    public String uniqueString;
    public UUID qrString=UUID.fromString("12c1289f-62c3-418d-81d8-531dfbc4581c");

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public void setDates(String dates) {
        this.dates = dates;
    }

    public void setUniqueString(String uniqueString) {
        this.uniqueString = uniqueString;
    }

    public String getEmail() {
        return email;
    }

    public float getTemperature() {
        return temperature;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getLocation() {
        return location;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getDates() {
        return dates;
    }

    public String getUniqueString() {
        return uniqueString;
    }

 }
