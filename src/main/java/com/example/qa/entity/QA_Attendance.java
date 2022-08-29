package com.example.qa.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.UUID;

@Document(collection = "attendance_qa")
public class QA_Attendance {

    private String email;
    private float temperature;
    private double latitude;
    private double longitude;
    private String location;
    private long timestamp;
    public UUID qrString=UUID.fromString("12c1289f-62c3-418d-81d8-531dfbc4581c");

    public QA_Attendance(){
        this.setLatitude(12.9158188);
        this.setLongitude(77.6353741);
    }

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
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.FLOOR);

        double bangaloreLatitude = 12.91;
        double bangaloreLongitude = 77.63;

        double hyderabadLatitude = 17.42;
        double hyderabadLongitude = 78.33;

        double puneLatitude = 18.53;
        double puneLongitude = 73.87;

        double latitudeCheck = Double.parseDouble(df.format(getLatitude()));
        double longitudeCheck = Double.parseDouble(df.format(getLongitude()));

        if(latitudeCheck == bangaloreLatitude && longitudeCheck == bangaloreLongitude) {
            this.location = "Bangalore";
        } else if (latitudeCheck == hyderabadLatitude && longitudeCheck == hyderabadLongitude) {
            this.location = "Hyderabad";
        } else if (latitudeCheck == puneLatitude && longitudeCheck == puneLongitude){
            this.location = "Pune";
        } else {
            this.location = "Out of Office";
        }
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
 }
