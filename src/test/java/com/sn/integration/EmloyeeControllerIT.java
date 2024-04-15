package com.sn.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sn.model.Employee;
import com.sn.repository.EmployeeRepository;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
@ActiveProfiles("test")
public class EmloyeeControllerIT {

    @Container
    private static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:latest").withUsername("username").withPassword("pass").withDatabaseName("ems").withUrlParam("currentSchema","dev");

    static {


    }


    @DynamicPropertySource
    public static void registerPgProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.datasource.schema", () -> "dev" );
        registry.add("spring.liquibase.contexts", () -> "!prod");
    }


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ObjectMapper objectMapper ;

   /* @BeforeEach
    void setUp(){
        employeeRepository.deleteAll();
    }
  */
    //Junit test for
    @Test
    public void givenEmployyeObject_whenCreated_thenRetunSavedEmployee() throws Exception {
        System.out.println("Postgres Details ");
        System.out.println(postgreSQLContainer.getJdbcUrl());
        System.out.println(postgreSQLContainer.getUsername());

        //precondition or set up
        Employee employee = Employee.builder().id(1L).firstName("Rishav").lastName("Dutta").email("satyajit.ray@gmail.com").build();


        //when - action or the behavior that we are going to test
        ResultActions response = mockMvc.perform(post("/api/employees/save").contentType(
                MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(employee)));



        //then -verify the output
       response.andDo(print()).andExpect(MockMvcResultMatchers.status().isCreated()).andExpect(MockMvcResultMatchers.jsonPath("$.firstName" , CoreMatchers.is(employee.getFirstName())));

    }

}
