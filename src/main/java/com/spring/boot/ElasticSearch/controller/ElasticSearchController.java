package com.spring.boot.ElasticSearch.controller;

import com.spring.boot.ElasticSearch.models.Employee;
import com.spring.boot.ElasticSearch.service.IElasticBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add")
    public Employee addEmployeeDetails(@RequestBody Employee e){
        return  elasticBeanFactory.getElasticSearchService().addEmployeeDetails(e);
    }

    @GetMapping("/getDetailsByAddress")
    public List<Employee> getEmployeeDetailsByAddress(@RequestParam String address){
        return  elasticBeanFactory.getElasticSearchService().getEmloyeeByAddress(address);
    }

}
