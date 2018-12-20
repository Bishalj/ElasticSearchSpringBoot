package com.spring.boot.ElasticSearch.models;

import java.util.Date;
import java.util.List;

public class Employee {
    private String id;
    private String name;
    private String address;
    private Date joiningDate;
    private int monthlySalary;
    private List<String> hobbies;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public int getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(int monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHoobies(List<String> hobbies) {
        this.hobbies = hobbies;
    }
}
