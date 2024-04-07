package com.sn.repository;

import com.sn.model.Employee;
import static org.assertj.core.api.Assertions.assertThat;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@Slf4j
public class EmployeeRepositoryTests
{
    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee(){
        Employee employee =Employee.builder().firstName("Subhasish").lastName("Nag").email("subhasish.nag@outlook.com").build();

        Employee savedEmployee = employeeRepository.save(employee);

        assertThat(savedEmployee).isNotNull();

        log.info(" Employee ID {} ",savedEmployee.getId());
        assertThat(savedEmployee.getId()).isGreaterThan(0);

    }

    //Junit test for
    @Test
    public void given_when_then(){
        //precondition or set up
        Employee employee =Employee.builder().firstName("Subhasish").lastName("Nag").email("subhasish.nag@outlook.com").build();

        Employee employee1 =Employee.builder().firstName("Remesh").lastName("Das").email("r.das@outlook.com").build();

        employeeRepository.save(employee);
        employeeRepository.save(employee1);



        //when - action or the behavior that we are going to test
        List<Employee> employeeList = employeeRepository.findAll();


        //then -verify the output
        assertThat(employeeList).isNotEmpty();
        assertThat(employeeList.size()).isEqualTo(2);


    }
}



