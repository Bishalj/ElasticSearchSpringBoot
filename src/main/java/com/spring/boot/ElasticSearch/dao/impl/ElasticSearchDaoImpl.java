package com.spring.boot.ElasticSearch.dao.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.boot.ElasticSearch.configuration.ElasticSearchConfig;
import com.spring.boot.ElasticSearch.dao.IElasticSearchDao;
import com.spring.boot.ElasticSearch.models.Employee;
import com.spring.boot.ElasticSearch.service.IElasticBeanFactory;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.spring.boot.ElasticSearch.constants.ElasticConstants.ELASTIC_INDEX;
import static com.spring.boot.ElasticSearch.constants.ElasticConstants.ELASTIC_TYPE;

@Repository
public class ElasticSearchDaoImpl implements IElasticSearchDao {
    private ObjectMapper objectMapper = new ObjectMapper();
    private Logger logger = LoggerFactory.getLogger(ElasticSearchDaoImpl.class);

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
        IndexRequest indexRequest = new IndexRequest(ELASTIC_INDEX,ELASTIC_TYPE)
                                                        .source(dataMap);
        try {
            IndexResponse response = ElasticSearchConfig.getReactiveClient().index(indexRequest);
            System.out.print(response);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return e;
    }

    @Override
    public List<Employee> searchEmployeesByAddress(String address) {
       SearchResponse searchResponse = null;
    	try {
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder()
                    .query(QueryBuilders.matchQuery("address", address))
                    .size(1000);

            SearchRequest searchRequest = new SearchRequest(ELASTIC_INDEX)
            		.types(ELASTIC_TYPE)
                    .source(searchSourceBuilder);
            searchResponse = elasticBeanFactory.getElasticSearchQueryDao().findBySearchQuery(searchRequest);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
        }
    	List<Employee> employees = mapSearchResponseToEmployee(Arrays.asList(searchResponse.getHits().getHits()));
        return employees ;
    }

	private List<Employee> mapSearchResponseToEmployee(List<SearchHit> searchHists) {
    	List<Employee> employeess =  new ArrayList<Employee>();
		searchHists.forEach( searchHit -> {
			JsonNode jsonObjectMapper = objectMapper.convertValue(searchHit, JsonNode.class);
			Employee employee =  objectMapper.convertValue(jsonObjectMapper.get("sourceAsMap"), Employee.class);
			employeess.add(employee);
		});
		return employeess;
	}

}
