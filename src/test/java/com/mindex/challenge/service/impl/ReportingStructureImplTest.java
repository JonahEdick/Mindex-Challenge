package com.mindex.challenge.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindex.challenge.data.ReportingStructure;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureImplTest {
    
    private String reportingUrl;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private Dictionary<String, Integer> emplpoyeeTests;

    @Before
    public void setup(){
        reportingUrl = "http://localhost:" + port + "/reporting/{id}";

        emplpoyeeTests = new Hashtable<>();
        emplpoyeeTests.put("16a596ae-edd3-4847-99fe-c4518e82c86f", 4);
        emplpoyeeTests.put("b7839309-3348-463b-a7e3-5de1c168beb3", 0);
        emplpoyeeTests.put("03aa1462-ffa9-4978-901b-7c001562cf6f", 2);
        emplpoyeeTests.put("62c1084e-6e34-4630-93fd-9153afb65309", 0);
        emplpoyeeTests.put("c0c2293d-16bd-4603-8e08-638a9d18b22c", 0);
    }

    @Test
    public void testNullCall(){
        HttpStatus actual = restTemplate.getForEntity(reportingUrl, Exception.class, "").getStatusCode();
        assertErrorResponse(HttpStatus.NOT_FOUND, actual);
    }

    @Test
    public void testInvalidId(){
        HttpStatus actual = restTemplate.getForEntity(reportingUrl, Exception.class, "intentional-failure").getStatusCode();
        assertErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, actual);
    }

    @Test
    public void testValidReports(){
        ReportingStructure result;

        Enumeration<String> employeeIds = emplpoyeeTests.keys();
        String currId;
        while(employeeIds.hasMoreElements()){
            currId = employeeIds.nextElement();
            result = restTemplate.getForEntity(reportingUrl, ReportingStructure.class, currId).getBody();
            assertReportingEquivalence(emplpoyeeTests.get(currId), result);
        }
    }

    private static void assertErrorResponse(HttpStatus expected, HttpStatus actual){
        assertEquals(expected, actual);
    }

    private static void assertReportingEquivalence(int expected, ReportingStructure actual){
        assertEquals(expected, actual.getNumberOfReports());
    }
}
