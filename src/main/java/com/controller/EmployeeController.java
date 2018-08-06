package com.controller;

import com.domain.Employee;
import com.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employees/find")
    public List<Employee> findAll() {

        return employeeService.findAll();
    }

    @PostMapping("/employees/save")
    public Employee saveEmployee(@Valid @RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @RequestMapping("/employees/find/{id}")
    public Employee findEmployee(@PathVariable(value = "id") Long id) {

        return employeeService.findOne(id);
    }

    @DeleteMapping("/employees/delete/{id}")
    public void deleteEmployee(@PathVariable(value = "id") Long id) {
        employeeService.delete(id);
    }

    @PutMapping("/employees/update/{id}")
    public Employee updateEmployee(@PathVariable(value = "id") Long id,
                                   @Valid @RequestBody Employee employeeDel) {
        return employeeService.update(id, employeeDel);
    }


}