package com.spring.boot.ElasticSearch.dao.impl;

import com.spring.boot.ElasticSearch.configuration.ElasticSearchConfig;
import com.spring.boot.ElasticSearch.dao.IElasticSearchQueryDao;
import com.spring.boot.ElasticSearch.service.IElasticBeanFactory;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
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
    public SearchResponse findBySearchQuery(SearchRequest searchRequest)throws IOException {
        return ElasticSearchConfig.getReactiveClient().search(searchRequest);
    }
}
