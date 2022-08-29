package com.example.dev.services;

import com.example.dev.entity.Qrcode;

import java.util.List;

public interface QrcodeService {
    Qrcode saveQrcode(Qrcode qrcode);

    List<Qrcode> getQrcode();

    boolean isUniqueIdExists(String uniqueId);
}
