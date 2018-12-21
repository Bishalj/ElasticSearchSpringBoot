package com.spring.boot.ElasticSearch.dao;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;

import java.io.IOException;
import java.util.List;

public interface IElasticSearchQueryDao {

    SearchResponse findBySearchQuery(SearchRequest searchRequest) throws IOException;
}
