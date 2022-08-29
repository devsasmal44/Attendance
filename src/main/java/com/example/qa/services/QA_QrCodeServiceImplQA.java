package com.example.qa.services;

import com.example.qa.entity.QA_Qrcode;
import com.example.qa.repository.QA_QrRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QA_QrCodeServiceImplQA implements QA_QrcodeService {
    private QA_QrRepo qrRepo;

    public QA_QrCodeServiceImplQA(QA_QrRepo qrRepo) {
        this.qrRepo = qrRepo;
    }

    @Override
    public QA_Qrcode saveQrcode(QA_Qrcode qrcode){

        return qrRepo.save(qrcode);
    }

    @Override
    public List<QA_Qrcode> getQrcode(){
        return qrRepo.findAll();
    }

    @Override
    public boolean isUniqueIdExists(String uniqueId) {
        if (qrRepo.existsById(uniqueId)) {
            return true;
        }
        return false;
    }
}
