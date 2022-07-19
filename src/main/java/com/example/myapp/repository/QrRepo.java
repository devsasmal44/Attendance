package com.example.myapp.repository;

import com.example.myapp.entity.Qrcode;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QrRepo extends MongoRepository<Qrcode, String> {

}
