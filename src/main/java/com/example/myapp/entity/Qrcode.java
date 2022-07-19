package com.example.myapp.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "qr")
public class Qrcode {

    @Id
    private String uniqueId;
    private long currentTime;
    private long expiryTime;
    private long compareTime;

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public void setExpiryTime(long expiryTime) {
        this.expiryTime = Instant.now().getEpochSecond() + 15*60;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public long getExpiryTime() {
        return expiryTime;
    }
}
