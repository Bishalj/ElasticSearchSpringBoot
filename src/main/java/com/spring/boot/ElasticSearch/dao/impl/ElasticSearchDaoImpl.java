package com.spring.boot.ElasticSearch.dao.impl;

import com.spring.boot.ElasticSearch.dao.IElasticSearchDao;
import com.spring.boot.ElasticSearch.models.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ElasticSearchDaoImpl implements IElasticSearchDao {
    @Override
    public List<Employee> getAllEmployeesDetail() {
        Employee e  = new Employee();
        e.setAddress("1234r");
        List<Employee> employees = new ArrayList<>();
        employees.add(e);
        return employees;
    }

    @Override
    public Employee addEmployeeDetails() {
        return null;
    }
}
