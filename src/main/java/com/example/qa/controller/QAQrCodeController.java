package com.example.qa.controller;

import com.example.qa.entity.QA_Attendance;
import com.example.qa.entity.QA_Qrcode;
import com.example.qa.repository.QA_QrRepo;
import com.example.qa.services.QA_QrcodeService;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/qrcode_qa")
@CrossOrigin
public class QAQrCodeController {
    private QA_QrcodeService qrcodeService;
    private QA_QrRepo qrRepo;

    QA_Qrcode qrcode = new QA_Qrcode();
    QA_Attendance attendance = new QA_Attendance();

    QAQrCodeController(QA_QrcodeService qrcodeService, QA_QrRepo qrRepo){
        this.qrcodeService = qrcodeService;
        this.qrRepo = qrRepo;
    }


    @GetMapping ("/qa/save")
    public QA_Qrcode saveQrcode() {
        qrcode.setUniqueId(String.valueOf(UUID.randomUUID()));
        qrcode.setExpiryTime(qrcode.getExpiryTime());
        qrcode.setCurrentTime(Instant.now().getEpochSecond());
        return qrcodeService.saveQrcode(qrcode);
    }

    @GetMapping("/qa/uniqueId")
    public String getUniqueId() {
        return qrcode.getUniqueId();
    }

    @GetMapping("/qa/list")
    public List<QA_Qrcode> getQrcode() {
        return qrcodeService.getQrcode();
    }


    @GetMapping("/qa/exists")
    @ResponseBody
    public String isUniqueIdExists() {
        String id = String.valueOf(attendance.qrString);
        if (qrcodeService.isUniqueIdExists(id)) {
            Optional<QA_Qrcode> optional = qrRepo.findById(id);
            QA_Qrcode qrResponse = optional.get();
            //if(>Instant.now().getEpochSecond(a))
                return "Qrcode found";
            //else
            //    return "Qrcode expired";
        } else {
            return "Qr doesn't exists for id";
        }
    }
}
