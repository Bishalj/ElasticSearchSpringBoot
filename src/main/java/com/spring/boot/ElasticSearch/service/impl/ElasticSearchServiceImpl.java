package com.spring.boot.ElasticSearch.service.impl;

import com.spring.boot.ElasticSearch.models.Employee;
import com.spring.boot.ElasticSearch.service.IElasticBeanFactory;
import com.spring.boot.ElasticSearch.service.IElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElasticSearchServiceImpl implements IElasticSearchService {

    @Autowired
    private IElasticBeanFactory elasticBeanFactory;
    @Override
    public List<Employee> getAllEmployeesDetail() {
        return elasticBeanFactory.getElasticSearchDao().getAllEmployeesDetail();
    }

    @Override
    public Employee addEmployeeDetails() {
        return elasticBeanFactory.getElasticSearchDao().addEmployeeDetails();
    }
}
