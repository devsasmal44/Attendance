package com.example.qa.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "employee_qa")
public class QA_Employee {
    private String email;
    private String name;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
