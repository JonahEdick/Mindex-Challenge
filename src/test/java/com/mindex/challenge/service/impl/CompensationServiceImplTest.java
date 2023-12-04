package com.mindex.challenge.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {
    private String compensationPostUrl;
    private String compensationGetUrl;

    @Autowired EmployeeService employeeService;
    
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup(){
        compensationPostUrl = "http://localhost:" + port + "/compensation";
        compensationGetUrl = "http://localhost:" + port + "/compensation/{id}";
    }

    @Test
    public void testCreateRead(){
        String testID = "16a596ae-edd3-4847-99fe-c4518e82c86f";
        Employee testEmployee = employeeService.read(testID);
        int testSalary = 84000;
        LocalDate testDate = LocalDate.now();
        Compensation testCompensation = new Compensation(testEmployee, testSalary, testDate);

        Compensation result = restTemplate.postForEntity(compensationPostUrl, testCompensation, Compensation.class).getBody();

        assertNotNull(result);
        assertEquals(testCompensation, result);
        
        result = restTemplate.getForEntity(compensationGetUrl, Compensation.class, testID).getBody();

        assertNotNull(result);
        assertEquals(testCompensation, result);
    }
}
