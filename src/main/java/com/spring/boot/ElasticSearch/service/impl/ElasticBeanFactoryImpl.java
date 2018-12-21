package com.spring.boot.ElasticSearch.service.impl;

import com.spring.boot.ElasticSearch.configuration.ElasticSearchConfig;
import com.spring.boot.ElasticSearch.dao.IElasticSearchDao;
import com.spring.boot.ElasticSearch.service.IElasticBeanFactory;
import com.spring.boot.ElasticSearch.service.IElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ElasticBeanFactoryImpl implements IElasticBeanFactory {

    @Autowired
    private IElasticSearchDao elasticSearchDao;

    @Autowired
    private  IElasticSearchService elasticSearchService;

    @Autowired
    private ElasticSearchConfig elasticSearchConfig;
    @Override
    public IElasticSearchDao getElasticSearchDao() {
        return elasticSearchDao;
    }

    @Override
    public IElasticSearchService getElasticSearchService() {
        return elasticSearchService;
    }

}
