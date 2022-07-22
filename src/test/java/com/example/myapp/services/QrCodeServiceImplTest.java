package com.example.myapp.services;

import com.example.myapp.entity.Qrcode;
import com.example.myapp.repository.QrRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
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