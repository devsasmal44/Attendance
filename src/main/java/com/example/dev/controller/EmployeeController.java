package com.example.dev.controller;


import com.example.dev.entity.Employee;
import com.example.dev.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@CrossOrigin
public class EmployeeController {

    private EmployeeService employeeService;

    EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }


    @PostMapping("/save")
    public void saveEmployee(@RequestBody Employee employee) {
         employeeService.saveEmployee(employee);
    }


    @GetMapping("/list")
    public List<Employee> getEmployee() {
        return employeeService.getEmployee();
    }

}
