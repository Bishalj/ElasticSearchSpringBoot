package com.spring.boot.ElasticSearch.controller;

import com.spring.boot.ElasticSearch.models.Employee;
import com.spring.boot.ElasticSearch.service.IElasticBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class ElasticSearchController {

    @Autowired
    private IElasticBeanFactory elasticBeanFactory;

    @GetMapping("/")
    public List<Employee> getAllEmployeesDetails(){
        return  elasticBeanFactory.getElasticSearchService().getAllEmployeesDetail();
    }

    @PostMapping("/")
    public Employee addEmployeeDetails(){
        return  elasticBeanFactory.getElasticSearchService().getAllEmployeesDetail();
    }

}
