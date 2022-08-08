package com.example.myapp.entity;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.UUID;

@Document(collection = "attendance")
public class Attendance {

    private String email;
    private float temperature;

    @Transient
    private double latitude;
    @Transient
    private double longitude;
    private String location;
    private long timestamp;
    public UUID qrString=UUID.fromString("12c1289f-62c3-418d-81d8-531dfbc4581c");

    public Attendance(){
        this.setLatitude(12.91);
        this.setLongitude(77.63);
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
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

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
