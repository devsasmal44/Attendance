package com.example.myapp.controller;

import com.example.myapp.entity.Attendance;
import com.example.myapp.entity.Qrcode;
import com.example.myapp.repository.QrRepo;
import com.example.myapp.services.QrcodeService;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.*;

@RestController
@RequestMapping("/qrcode")
public class QrCodeController {
    private QrcodeService qrcodeService;
    private QrRepo qrRepo;

    Qrcode qrcode = new Qrcode();
    Attendance attendance = new Attendance();

    QrCodeController(QrcodeService qrcodeService, QrRepo qrRepo){
        this.qrcodeService = qrcodeService;
        this.qrRepo = qrRepo;
    }

    @CrossOrigin
    @GetMapping ("/save")
    public Qrcode saveQrcode() {
        qrcode.setUniqueId(String.valueOf(UUID.randomUUID()));
        qrcode.setExpiryTime(qrcode.getExpiryTime());
        qrcode.setCurrentTime(Instant.now().getEpochSecond());
        return qrcodeService.saveQrcode(qrcode);
    }

    @CrossOrigin
    @GetMapping("/uniqueId")
    public String getUniqueId() {
        return qrcode.getUniqueId();
    }

    @CrossOrigin
    @GetMapping("/list")
    public List<Qrcode> getQrcode() {
        return qrcodeService.getQrcode();
    }

    @CrossOrigin
    @GetMapping("/exists")
    @ResponseBody
    public String isUniqueIdExists() {
        String id = String.valueOf(attendance.qrString);
        if (qrcodeService.isUniqueIdExists(id)) {
            Optional<Qrcode> optional = qrRepo.findById(id);
            Qrcode qrResponse = optional.get();
            //if(>Instant.now().getEpochSecond(a))
                return "Qrcode found";
            //else
            //    return "Qrcode expired";
        } else {
            return "Qr doesn't exists for id";
        }
    }
}
