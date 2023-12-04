package com.mindex.challenge.data;

import java.util.List;
import java.util.HashSet;
import java.util.Set;

import com.mindex.challenge.dao.EmployeeRepository;

public class ReportingStructure {
    private Employee employee;
    private int numberOfReports = 0;

    /**
     * 
     */
    public ReportingStructure(){

    }

    /**
     * 
     * @param employee
     */
    public ReportingStructure(Employee employee, EmployeeRepository employeeRepository){
        this.employee = employee;

        List<Employee> directReports = this.employee.getDirectReports();

        if(directReports != null){
            Set<Employee> reportSet = new HashSet<>();
            Employee tempReport;
            List<Employee> subReports;
            for (Employee directReport : directReports) {
                tempReport = employeeRepository.findByEmployeeId(directReport.getEmployeeId());
                reportSet.add(tempReport);
                subReports = tempReport.getDirectReports();
                if(subReports != null)
                    for(Employee subReport : subReports){
                        reportSet.add(employeeRepository.findByEmployeeId(subReport.getEmployeeId()));
                    }
            }
            this.numberOfReports = reportSet.size();
        }        
    }

    /**
     * 
     * @return
     */
    public int getNumberOfReports(){
        return numberOfReports;
    }

    /**
     * 
     * @return
     */
    public Employee getEmployee(){
        return employee;
    }
}
