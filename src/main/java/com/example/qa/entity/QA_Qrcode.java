package com.example.qa.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Document(collection = "qr_qa")
public class QA_Qrcode {

    @Id
    private String uniqueId;
    private long currentTime;
    private long expiryTime;
    private long compareTime;

    public QA_Qrcode() {
        this.uniqueId = String.valueOf(UUID.randomUUID());
        this.currentTime = Instant.now().getEpochSecond();
        this.expiryTime = this.currentTime+(2*60);
    }

    public void setUniqueId(String uniqueId) {
//        this.uniqueId =String.valueOf(UUID.randomUUID());
    }


    public void setExpiryTime(long expiryTime) {
//        this.expiryTime = Instant.now().getEpochSecond() + 15*60;
    }

    public void setCurrentTime(long currentTime) {
//        this.currentTime = currentTime;
    }


    private void updateQr() {
        this.uniqueId = String.valueOf(UUID.randomUUID());
        this.currentTime = Instant.now().getEpochSecond();
        this.expiryTime = this.currentTime + (2*60);

    }
    public String getUniqueId() {

        if(Instant.now().getEpochSecond()>this.expiryTime) {
            updateQr();
        }
        return uniqueId;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public long getExpiryTime() {
        return expiryTime;
    }
}
