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
    public List<Employee> getEmloyeeByAddress(String address) {
        return elasticBeanFactory.getElasticSearchDao().searchEmployeesByAddress(address);
    }

    @Override
    public List<Employee> getAllEmployeesDetail() {
        return elasticBeanFactory.getElasticSearchDao().getAllEmployeesDetail();
    }

    @Override
    public Employee addEmployeeDetails(Employee e) {
        return elasticBeanFactory.getElasticSearchDao().addEmployeeDetails(e);
    }

    @Override
    public List<String> getAllEmployeesNames() {
        return elasticBeanFactory.getElasticSearchDao().getAllEmployeesNames();
    }

    @Override
    public Employee getEmployeesById(String id) {
        return elasticBeanFactory.getElasticSearchDao().getEmployeesById(id);
    }

    @Override
	public Employee updateEmloyeeDetailById(String id, Employee employee) {
		// TODO Auto-generated method stub
		return elasticBeanFactory.getElasticSearchDao().updateEmployeesById(id,employee);
	}
}
