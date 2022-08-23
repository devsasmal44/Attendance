package com.example.myapp.controller;


import com.example.myapp.entity.Employee;
import com.example.myapp.services.EmployeeService;
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

    @CrossOrigin
    @PostMapping("/save")
    public void saveEmployee(@RequestBody Employee employee) {
         employeeService.saveEmployee(employee);
    }

    @CrossOrigin
    @GetMapping("/list")
    public List<Employee> getEmployee() {
        return employeeService.getEmployee();
    }

}
