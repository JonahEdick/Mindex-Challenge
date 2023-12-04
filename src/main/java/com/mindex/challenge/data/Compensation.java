package com.mindex.challenge.data;

import java.time.LocalDate;

public class Compensation {
    Employee employee;
    int salary;
    LocalDate effectiveDate;

    public Compensation(Employee employee, int salary, LocalDate effectiveDate){
        this.employee = employee;
        this.salary = salary;
        this.effectiveDate = effectiveDate;
    }

    public Employee getEmployee(){
        return employee;
    }

    public int getSalary(){
        return salary;
    }

    public LocalDate getEffectiveDate(){
        return effectiveDate;
    }

    @Override
    public boolean equals(Object test){
        // Checks to see if they are the same instantiated object.
        if(test == this) return true;

        // Checks to see if the tested object is the same class.
        if(!(test instanceof Compensation)) return false;

        Compensation testCompensation = (Compensation) test;

        return 
            this.employee.equals(testCompensation.getEmployee()) &&
            this.effectiveDate.equals(testCompensation.getEffectiveDate()) &&
            this.salary == testCompensation.getSalary()
        ;
    }
}
