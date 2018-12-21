package com.spring.boot.ElasticSearch.dao;

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

import com.spring.boot.ElasticSearch.models.Employee;

import java.io.IOException;
import java.util.List;

public interface IElasticSearchQueryDao {

    SearchResponse findBySearchQuery(SearchRequest searchRequest) throws IOException;
    
    IndexResponse insert(IndexRequest indexRequest)throws IOException;
    
    DeleteResponse delete(DeleteRequest deleteRequest)throws IOException;
    
    UpdateResponse update(UpdateRequest updateRequest)throws IOException;

    GetResponse getById(GetRequest getRequest)throws IOException;
}
