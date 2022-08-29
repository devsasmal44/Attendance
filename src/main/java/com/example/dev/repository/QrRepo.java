package com.example.dev.repository;

import com.example.dev.entity.Qrcode;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QrRepo extends MongoRepository<Qrcode, String> {

}
