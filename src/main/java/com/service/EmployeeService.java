package com.service;

import com.dao.EmployeeRepository;
import com.domain.Employee;
import com.exception.ResourceNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

@Repository
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    public List<Employee> findAll() {
        logger.info("查找全部");

        return employeeRepository.findAll();
    }

    public Employee save(Employee employee) {
        logger.info("插入数据");
        return employeeRepository.save(employee);
    }

    public Employee findOne(Long id) {
        logger.info("查找单条数据");
//        return employeeRepository.findById(id);
        return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));

    }

    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));

        logger.info("删除数据");
        employeeRepository.delete(employee);
        return ResponseEntity.ok().build();

    }

    public Employee update(Long id, Employee employeeDel) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));

        logger.info("修改数据");
        employee.setName(employeeDel.getName());
        employee.setGender(employeeDel.getGender());
        employee.setAge(employeeDel.getAge());
        Employee updatedEmployee = employeeRepository.save(employee);
        return updatedEmployee;
    }


}
