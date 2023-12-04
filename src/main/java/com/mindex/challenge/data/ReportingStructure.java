package com.mindex.challenge.data;

import java.util.List;
import java.util.HashSet;
import java.util.Set;

import com.mindex.challenge.dao.EmployeeRepository;

public class ReportingStructure {
    private Employee employee;
    
    // Default is 0 for an employee with no direct reports
    private int numberOfReports = 0;

    public ReportingStructure(){}

    public ReportingStructure(Employee employee, EmployeeRepository employeeRepository){
        this.employee = employee;

        // List of all relavent reports
        List<Employee> directReports = this.employee.getDirectReports();

        if(directReports != null){
            // Create set to store total reports
            Set<Employee> reportSet = new HashSet<>();
            Employee tempReport;
            List<Employee> subReports;

            // Iterate through all direct reports
            for (Employee directReport : directReports) {
                tempReport = employeeRepository.findByEmployeeId(directReport.getEmployeeId());
                reportSet.add(tempReport);
                subReports = tempReport.getDirectReports();

                // Iterate through potential distinct reports
                if(subReports != null)
                    for(Employee subReport : subReports){
                        reportSet.add(employeeRepository.findByEmployeeId(subReport.getEmployeeId()));
                    }
            }
            this.numberOfReports = reportSet.size();
        }        
    }

    public int getNumberOfReports(){
        return numberOfReports;
    }

    public Employee getEmployee(){
        return employee;
    }

    @Override
    public boolean equals(Object test){
        // Checks to see if they are the same instantiated object.
        if(test == this) return true;

        // Checks to see if the tested object is the same class.
        if(!(test instanceof ReportingStructure)) return false;

        ReportingStructure testStructure = (ReportingStructure) test;

        return
            this.employee.equals(testStructure.getEmployee()) &&
            this.numberOfReports == testStructure.getNumberOfReports()
        ;
    }
}
