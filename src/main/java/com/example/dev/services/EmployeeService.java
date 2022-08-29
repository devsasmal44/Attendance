package com.example.dev.services;


import com.example.dev.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);

    List<Employee> getEmployee();
}
