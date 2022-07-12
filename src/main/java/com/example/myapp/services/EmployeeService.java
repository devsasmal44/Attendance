package com.example.myapp.services;


import com.example.myapp.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);

    List<Employee> getEmployee();
}
