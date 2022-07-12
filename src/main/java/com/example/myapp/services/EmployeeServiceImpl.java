package com.example.myapp.services;


import com.example.myapp.entity.Employee;
import com.example.myapp.repository.EmployeeRepo;
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
