package com.example.qa.services;


import com.example.qa.entity.QA_Employee;
import com.example.qa.repository.QA_EmployeeRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QA_EmployeeServiceImpl implements QA_EmployeeService {

    private QA_EmployeeRepo employeeRepo;

    QA_EmployeeServiceImpl(QA_EmployeeRepo employeeRepo){
        this.employeeRepo = employeeRepo;
    }

    @Override
    public QA_Employee saveEmployee(QA_Employee employee){
        return employeeRepo.save(employee);
    }

    @Override
    public List<QA_Employee> getEmployee() {
        return employeeRepo.findAll();
    }

}
