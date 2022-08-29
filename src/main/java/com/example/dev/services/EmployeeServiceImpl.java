package com.example.dev.services;


import com.example.dev.entity.Employee;
import com.example.dev.repository.EmployeeRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepo employeeRepo;

    EmployeeServiceImpl(EmployeeRepo employeeRepo){
        this.employeeRepo = employeeRepo;
    }

    @Override
    public Employee saveEmployee(Employee employee){
        return employeeRepo.save(employee);
    }

    @Override
    public List<Employee> getEmployee() {
        return employeeRepo.findAll();
    }

}
