package com.spring.boot.ElasticSearch.dao.impl;

import com.spring.boot.ElasticSearch.configuration.ElasticSearchConfig;
import com.spring.boot.ElasticSearch.dao.IElasticSearchQueryDao;
import com.spring.boot.ElasticSearch.models.Employee;
import com.spring.boot.ElasticSearch.service.IElasticBeanFactory;

import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public class ElasticSearchQueryDaoImpl implements IElasticSearchQueryDao {

    @Autowired
    IElasticBeanFactory elasticBeanFactory;


    @Override
    public SearchResponse search(SearchRequest searchRequest)throws IOException {
        return ElasticSearchConfig.getReactiveClient().search(searchRequest);
    }


	@Override
	public IndexResponse insert(IndexRequest indexRequest) throws IOException {
		return ElasticSearchConfig.getReactiveClient().index(indexRequest);
	}


	@Override
	public DeleteResponse delete(DeleteRequest deleteRequest) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public UpdateResponse update(UpdateRequest updateRequest) throws IOException {
		// TODO Auto-generated method stub
		return ElasticSearchConfig.getReactiveClient().update(updateRequest);
	}


	@Override
	public GetResponse getById(GetRequest getRequest) throws IOException {
		// TODO Auto-generated method stub
		return ElasticSearchConfig.getReactiveClient().get(getRequest);
	}
}
