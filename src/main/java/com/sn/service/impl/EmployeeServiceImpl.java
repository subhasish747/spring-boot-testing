package com.sn.service.impl;

import com.sn.exception.ResourceNotFoundException;
import com.sn.model.Employee;
import com.sn.repository.EmployeeRepository;
import com.sn.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EmployeeServiceImpl implements EmployeeService {


    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository ;
    }

    @Override
    public Employee saveEmployee(Employee employee) throws ResourceNotFoundException{
        Optional<Employee> savedEmployee= employeeRepository.findByEmail(employee.getEmail());
        if(savedEmployee.isPresent()){
            throw new ResourceNotFoundException("Employee already exists with given email "+employee.getEmail());
        }
        return employeeRepository.save(employee);
    }

    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id){
        return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No employee found with this id "+id));
    }

}
