package com.spring.boot.ElasticSearch.service;

import com.spring.boot.ElasticSearch.models.Employee;

import java.util.EmptyStackException;
import java.util.List;

public interface IElasticSearchService {
    List<Employee> getAllEmployeesDetail();

    Employee addEmployeeDetails(Employee e);

    List<Employee> getEmloyeeByAddress(String address);
}
