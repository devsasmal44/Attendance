package com.example.myapp.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "attendance")
public class Attendance {

    private String email;
    private double temperature;
    private String latitude;
    private String longitude;
    private String qrcodeString;
    private long timestamp;

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

    public void setQrcodeString(String qrcodeString) {
        this.qrcodeString = qrcodeString;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
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

    public String getQrcodeString() {
        return qrcodeString;
    }

    public long getTimestamp() {
        return timestamp;
    }

 }
