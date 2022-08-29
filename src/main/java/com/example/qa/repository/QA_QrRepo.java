package com.example.qa.repository;

import com.example.qa.entity.QA_Qrcode;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QA_QrRepo extends MongoRepository<QA_Qrcode, String> {

}
