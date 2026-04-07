package com.example.mvc3_j11.model;

public class Student {
    private String msv, name, faculty;
    private int year;
    private double gpa;

    public Student(String msv, String name, String faculty, int year, double gpa) {
        this.msv = msv; this.name = name; this.faculty = faculty; this.year = year; this.gpa = gpa;
    }
    public String getMsv() { return msv; }
    public String getName() { return name; }
    public String getFaculty() { return faculty; }
    public int getYear() { return year; }
    public double getGpa() { return gpa; }
}
