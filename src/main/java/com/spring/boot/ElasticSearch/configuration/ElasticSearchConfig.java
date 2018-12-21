package com.spring.boot.ElasticSearch.configuration;


import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchConfig{
    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticSearchConfig.class);

    @Value("${spring.data.elasticsearch.cluster-nodes}")
    private String clusterNodes;

    @Value("${spring.data.elasticsearch.cluster-name}")
    private String clusterName;

    private static RestHighLevelClient restHighLevelClient = null;

    public static void destroy() throws Exception {
        try {
            if (restHighLevelClient != null) {
                restHighLevelClient.close();
                LOGGER.info("Elastic Search client closed.");
            }
        } catch (final Exception e) {
            LOGGER.error("CLosing ElasticSearch client: " + e);
        }
    }


    private static RestHighLevelClient buildClient() {
        try {
            restHighLevelClient = new RestHighLevelClient(
                    RestClient.builder(
                            new HttpHost("localhost", 9200, "http")));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.info("Elastic Search Connected");
        return restHighLevelClient;
    }

    public static RestHighLevelClient getReactiveClient(){
        if(restHighLevelClient == null)
            return buildClient();
        return  restHighLevelClient;
    }
}
