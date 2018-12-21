package com.spring.boot.ElasticSearch.service;

import com.spring.boot.ElasticSearch.configuration.ElasticSearchConfig;
import com.spring.boot.ElasticSearch.dao.IElasticSearchDao;

public interface IElasticBeanFactory {

    IElasticSearchDao getElasticSearchDao();

    IElasticSearchService getElasticSearchService();

}
