package com.sn.service;

import com.sn.model.Employee;

import java.util.List;

public interface EmployeeService {

    public Employee saveEmployee(Employee employee);
    public List<Employee> getEmployees();

    public Employee getEmployeeById(Long id);
}
