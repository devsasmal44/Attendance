package com.example.dev.services;

import com.example.dev.entity.Qrcode;
import com.example.dev.repository.QrRepo;
import org.springframework.stereotype.Service;

import java.util.List;

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
