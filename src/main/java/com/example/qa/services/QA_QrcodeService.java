package com.example.qa.services;

import com.example.qa.entity.QA_Qrcode;

import java.util.List;

public interface QA_QrcodeService {
    QA_Qrcode saveQrcode(QA_Qrcode qrcode);

    List<QA_Qrcode> getQrcode();

    boolean isUniqueIdExists(String uniqueId);
}
