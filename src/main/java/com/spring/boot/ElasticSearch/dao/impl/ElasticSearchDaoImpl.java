package com.spring.boot.ElasticSearch.dao.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.boot.ElasticSearch.configuration.ElasticSearchConfig;
import com.spring.boot.ElasticSearch.dao.IElasticSearchDao;
import com.spring.boot.ElasticSearch.models.Employee;
import com.spring.boot.ElasticSearch.service.IElasticBeanFactory;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
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
    public Employee getEmployeesById(String id) {
        GetRequest getRequest = new GetRequest(ELASTIC_INDEX, ELASTIC_TYPE, id);
        Map<String, Object> sourceAsMap = new HashMap<>();
        try {
            GetResponse getResponse = elasticBeanFactory.getElasticSearchQueryDao().getById(getRequest);
            sourceAsMap = getResponse.getSourceAsMap();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        System.out.println(sourceAsMap);
        return objectMapper.convertValue(sourceAsMap, Employee.class);
    }

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
            IndexResponse response = elasticBeanFactory.getElasticSearchQueryDao().insert(indexRequest);
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
            searchResponse = elasticBeanFactory.getElasticSearchQueryDao().search(searchRequest);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
        }
    	List<Employee> employees = mapSearchResponseToEmployee(Arrays.asList(searchResponse.getHits().getHits()));
        return employees ;
    }

	private List<Employee> mapSearchResponseToEmployee(List<SearchHit> searchHists) {
    	List<Employee> employees =  new ArrayList<Employee>();
		searchHists.forEach( searchHit -> {
			JsonNode jsonObjectMapper = objectMapper.convertValue(searchHit, JsonNode.class);
			Employee employee =  objectMapper.convertValue(jsonObjectMapper.get("sourceAsMap"), Employee.class);
			employees.add(employee);
		});
		return employees;
	}

    @Override
    public List<String> getAllEmployeesNames() {
        SearchRequest searchRequest = new SearchRequest(ELASTIC_INDEX);
        searchRequest.types(ELASTIC_TYPE);
        String[] includeFields = new String[]{"name"};
        String[] excludeFields = new String[]{"id", "joiningDate", "address", "monthlySalary", "hobbies"};
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders
                .matchAllQuery())
                .size(10000);
        searchSourceBuilder.fetchSource(includeFields, excludeFields);
        searchRequest.source(searchSourceBuilder);

        List<String>employeeName = new ArrayList<>();

        SearchHits hits = new SearchHits(new SearchHit[0], 0, 0);

        try {
            SearchResponse searchResponse = elasticBeanFactory.getElasticSearchQueryDao().search(searchRequest);
            hits = searchResponse.getHits();
            System.out.println(hits.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(SearchHit documentFields: hits.getHits()){
            if(!employeeName.contains(documentFields.getSourceAsMap().get("name")))
                employeeName.add(documentFields.getSourceAsMap().get("name").toString());
        }
        return employeeName;
    }

    @Override
	public Employee updateEmployeesById(String id, Employee employee) {
		// TODO Auto-generated method stub
		UpdateRequest updateRequest = new UpdateRequest(ELASTIC_INDEX,ELASTIC_TYPE,id)
														.fetchSource(true);
		Map<String, Object> error = new HashMap<>();
        error.put("Error", "Unable to update article.");
        try {
            String employeeJson = objectMapper.writeValueAsString(employee);
            updateRequest.doc(employeeJson, XContentType.JSON);
            UpdateResponse updateResponse = elasticBeanFactory.getElasticSearchQueryDao().update(updateRequest);
            Map<String, Object> sourceAsMap = updateResponse.getGetResult().sourceAsMap();
            System.out.println(sourceAsMap);
            return objectMapper.convertValue(sourceAsMap, Employee.class);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
		return null;
	}


}
