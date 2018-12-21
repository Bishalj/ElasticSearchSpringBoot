package com.spring.boot.ElasticSearch.dao;

import com.spring.boot.ElasticSearch.models.Employee;

import java.util.List;

public interface IElasticSearchDao {
    List<Employee> getAllEmployeesDetail();

    Employee addEmployeeDetails(Employee e);

    List<Employee> searchEmployeesByAddress(String address);
    
    Employee updateEmployeesById(String id, Employee employee);
    
}
