package com.example.myapp.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "attendance")
public class Attendance {

    private String email;
    private float temperature;
    private double latitude;
    private double longitude;
    private long timestamp;
    public String dates;
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
    
    public void setDates(String dates) {
		this.dates = dates;
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

    public long getTimestamp() {
        return timestamp;
    }
    
    public String getDates() {
		return dates;
	}

 }
