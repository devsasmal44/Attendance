package com.example.myapp.services;

import com.example.dev.entity.Qrcode;
import com.example.dev.repository.QrRepo;
import com.example.dev.services.QrCodeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QrCodeServiceImplTest {
    QrCodeServiceImpl qrCodeService;
    QrRepo qrRepo;

    @Before
    public void setup(){
        qrRepo = Mockito.mock(QrRepo.class);
        qrCodeService = new QrCodeServiceImpl(qrRepo);
    }

    @Test
    public void saveQrCode_shouldReturnDifferentQrCode_whenQrCodeHasNotBeenExpired(){
        Qrcode qrcode = new Qrcode();
        when(qrRepo.save(qrcode)).thenReturn(qrcode);

        Qrcode savedQrCode = qrCodeService.saveQrcode(qrcode);
        assertEquals(savedQrCode, qrcode);
    }
}