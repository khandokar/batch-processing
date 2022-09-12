package com.example.batchprocessing.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Date;
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(max = 50)
    private String firstname;

    @Size(max = 50)
    private String lastname;

    @NotBlank
    private Double gpa;

    @NotBlank
    private Date dob;

    @Transient
    private int age;

    public Student() {
    }

    public Student(String firstName, String lastName, Double gpa, Date dob) {
        this.firstname = firstName;
        this.lastname = lastName;
        this.gpa = gpa;
        this.dob = dob;
    }

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstName) {
        this.firstname = firstName;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastName) {
        this.lastname = lastName;
    }

    public Double getGpa() {
        return gpa;
    }

    public void setGpa(Double gpa) {
        this.gpa = gpa;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "firstName: " + firstname + ", lastName: " + lastname + ", gpa: " + gpa + ", dob: " + dob;
    }
}
