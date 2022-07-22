package com.example.myapp.services;

import com.example.myapp.entity.Qrcode;
import com.example.myapp.repository.QrRepo;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class QrCodeServiceImpl implements QrcodeService{
    private QrRepo qrRepo;

    public QrCodeServiceImpl(QrRepo qrRepo) {
        this.qrRepo = qrRepo;
    }

    @Override
    public Qrcode saveQrcode(Qrcode qrcode){

        return qrRepo.save(qrcode);
    }

    @Override
    public List<Qrcode> getQrcode(){
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
