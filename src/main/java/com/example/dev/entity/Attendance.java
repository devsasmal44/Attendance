package com.example.dev.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.UUID;

@Document(collection = "attendance")
public class Attendance {
    private String name;
    private String email;
    private float temperature;
    private double latitude;
    private double longitude;
    private String location;
    private long timestamp;
    private String dates;
    public UUID qrString=UUID.fromString("12c1289f-62c3-418d-81d8-531dfbc4581c");

    public Attendance(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTemperature(float temperature) {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.FLOOR);
        this.temperature =Float.parseFloat(df.format(temperature));
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

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public float getTemperature() {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.FLOOR);
        return Float.parseFloat(df.format(temperature));
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
    public String getOfficeBranch(){
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
            location = "Bangalore";
        } else if (latitudeCheck == hyderabadLatitude && longitudeCheck == hyderabadLongitude) {
            location = "Hyderabad";
        } else if (latitudeCheck == puneLatitude && longitudeCheck == puneLongitude){
            location = "Pune";
        } else {
            location = "Out of Office";
        }
        return location;
    }
 }
