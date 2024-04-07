package com.sn.controller;

import com.sn.exception.ResourceNotFoundException;
import com.sn.model.Employee;
import com.sn.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public List<Employee> getEmployees(){
        log.info("Fetching all employee data ");

        return employeeService.getEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) throws ResourceNotFoundException{
        log.info("Fetching employee data for Employee Id {} ",id);
        return employeeService.getEmployeeById(id);
    }


    @PostMapping("/save")
    public Employee save(@RequestBody Employee employee) throws ResourceNotFoundException {
        return employeeService.saveEmployee(employee);
    }

    @GetMapping(value = "/test")
    public Employee testEmployees(){
        log.info("Fetching test employee data ");
        return Employee.builder().id(1L).firstName("Subhasish").lastName("Nag").email("subhasish.nag1@gmail.com").build();

    }

}
