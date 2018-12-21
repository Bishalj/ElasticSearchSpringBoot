package com.spring.boot.ElasticSearch.dao.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.boot.ElasticSearch.configuration.ElasticSearchConfig;
import com.spring.boot.ElasticSearch.dao.IElasticSearchDao;
import com.spring.boot.ElasticSearch.models.Employee;
import com.spring.boot.ElasticSearch.service.IElasticBeanFactory;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.spring.boot.ElasticSearch.constants.ElasticConstants.ELASTIC_INDEX;
import static com.spring.boot.ElasticSearch.constants.ElasticConstants.ELASTIC_TYPE;

@Repository
public class ElasticSearchDaoImpl implements IElasticSearchDao {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private IElasticBeanFactory elasticBeanFactory;
    @Override
    public List<Employee> getAllEmployeesDetail() {
        Employee e  = new Employee();
        e.setAddress("1234r");
        List<Employee> employees = new ArrayList<>();
        employees.add(e);
        return employees;
    }

    @Override
    public Employee addEmployeeDetails(Employee e) {
        Map<String,Object> dataMap = objectMapper.convertValue(e,Map.class);
        IndexRequest indexRequest = new IndexRequest(ELASTIC_INDEX,ELASTIC_TYPE,e.getId())
                                                        .source(dataMap);
        try {
            IndexResponse response = ElasticSearchConfig.getReactiveClient().index(indexRequest);
            System.out.print(response);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return e;
    }
}
