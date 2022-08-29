package com.example.qa.controller;


import com.example.qa.entity.QA_Employee;
import com.example.qa.services.QA_EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee_qa")
@CrossOrigin
public class QAEmployeeController {

    private QA_EmployeeService employeeService;

    QAEmployeeController(QA_EmployeeService employeeService){
        this.employeeService = employeeService;
    }


    @PostMapping("/qa/save")
    public void saveEmployee(@RequestBody QA_Employee employee) {
         employeeService.saveEmployee(employee);
    }


    @GetMapping("/qa/list")
    public List<QA_Employee> getEmployee() {
        return employeeService.getEmployee();
    }

}
