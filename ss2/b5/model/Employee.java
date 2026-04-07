package com.example.b5.model;

import java.util.Date;

public class Employee {
    private String code, fullName, department, status;
    private double salary;
    private Date joinDate;

    public Employee(String code, String fullName, String department, double salary, Date joinDate, String status) {
        this.code = code;
        this.fullName = fullName;
        this.department = department;
        this.salary = salary;
        this.joinDate = joinDate;
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public String getStatus() {
        return status;
    }
}