package com.example.myapp.services;

import com.example.myapp.entity.Qrcode;

import java.util.List;

public interface QrcodeService {
    Qrcode saveQrcode(Qrcode qrcode);

    List<Qrcode> getQrcode();

    boolean isUniqueIdExists(String uniqueId);
}
