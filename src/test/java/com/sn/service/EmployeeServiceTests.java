package com.sn.service;

import com.sn.exception.ResourceNotFoundException;
import com.sn.model.Employee;
import com.sn.repository.EmployeeRepository;
import static org.assertj.core.api.Assertions.assertThat;

import com.sn.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;

    @BeforeEach
    public void setUp(){
    //    employeeRepository = Mockito.mock(EmployeeRepository.class);
       // employeeService = new EmployeeServiceImpl(employeeRepository);
        employee =Employee.builder().id(1L).firstName("Subhasish").lastName("Nag").email("subhasish.nag1@gmail.com").build();

    }

    //Junit test for
    @Test
    public void givenEmployeeObject_whenSaveEmployee_thenRetutnEmployeeObject() {

        System.out.println(employeeService);
        System.out.println(employeeRepository);



        given(employeeRepository.findByEmail(employee.getEmail())).willReturn(Optional.empty());

        //precondition or set up
        given(employeeRepository.save(employee)).willReturn(employee);

        System.out.println(employeeService);

        //when - action or the behavior that we are going to test

        Employee savedEmployee = employeeService.saveEmployee(employee);
        System.out.println(savedEmployee.getId());
        //then -verify the output
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);

    }

    @Test
    public void givenEmployeeObject_whenSaveEmployee_thenRetutnException() {

        System.out.println(employeeService);
        System.out.println(employeeRepository);



        given(employeeRepository.findByEmail(employee.getEmail())).willReturn(Optional.of(employee));

    //    given(employeeRepository.save(employee)).willReturn(employee);

        //precondition or set up
    //    given(employeeRepository.save(employee)).willReturn(employee);

        System.out.println(employeeService);

        //when - action or the behavior that we are going to test

    //    Employee savedEmployee = employeeService.saveEmployee(employee);
    //    System.out.println(savedEmployee.getId());
        //then -verify the output
        Assertions.assertThrows(ResourceNotFoundException.class , () -> {
            employeeService.saveEmployee(employee);
        });
       // assertThr
        verify(employeeRepository , never()).save(any(Employee.class));
    }

}

