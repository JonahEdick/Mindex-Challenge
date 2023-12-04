package com.mindex.challenge.data;

import java.util.List;

public class Employee {
    private String employeeId;
    private String firstName;
    private String lastName;
    private String position;
    private String department;
    private List<Employee> directReports;

    public Employee() {
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<Employee> getDirectReports() {
        return directReports;
    }

    public void setDirectReports(List<Employee> directReports) {
        this.directReports = directReports;
    }

    @Override
    public boolean equals(Object test){
        // Checks to see if they are the same instantiated object.
        if(test == this) return true;

        // Checks to see if the tested object is the same class.
        if(!(test instanceof Employee)) return false;

        // Type cast tested object.
        Employee testEmployee = (Employee) test;

        // Test to see if contents of both objects are identical.
        return 
            this.firstName.equals(testEmployee.getFirstName()) &&
            this.lastName.equals(testEmployee.getLastName()) &&
            this.department.equals(testEmployee.getDepartment()) &&
            this.position.equals(testEmployee.getPosition())
        ;
    }
}
