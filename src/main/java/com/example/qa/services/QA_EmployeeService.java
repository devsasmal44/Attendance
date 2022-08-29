package com.example.qa.services;


import com.example.qa.entity.QA_Employee;

import java.util.List;

public interface QA_EmployeeService {
    QA_Employee saveEmployee(QA_Employee employee);

    List<QA_Employee> getEmployee();
}
