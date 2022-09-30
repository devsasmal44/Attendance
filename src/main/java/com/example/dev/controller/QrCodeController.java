package com.example.dev.controller;

import com.example.dev.entity.Attendance;
import com.example.dev.entity.Qrcode;
import com.example.dev.repository.QrRepo;
import com.example.dev.services.QrcodeService;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.*;

@RestController
@RequestMapping("/qrcode")
@CrossOrigin
public class QrCodeController {
    private QrcodeService qrcodeService;
    private QrRepo qrRepo;


    Qrcode qrcode = new Qrcode();
    Attendance attendance;

    QrCodeController(QrcodeService qrcodeService, QrRepo qrRepo){
        this.qrcodeService = qrcodeService;
        this.qrRepo = qrRepo;
    }

    @GetMapping ("/save")
    public Qrcode saveQrcode() {
        qrcode.setUniqueId(String.valueOf(UUID.randomUUID()));
        qrcode.setExpiryTime(qrcode.getExpiryTime());
        qrcode.setCurrentTime(Instant.now().getEpochSecond());
        return qrcodeService.saveQrcode(qrcode);
    }

    @GetMapping("/uniqueId")
    public String getUniqueId() {

        return qrcode.getUniqueId();
    }

    @GetMapping("/list")
    public List<Qrcode> getQrcode() {
        return qrcodeService.getQrcode();
    }

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
