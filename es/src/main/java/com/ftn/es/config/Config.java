package com.ftn.es.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
@EnableWebMvc
@Configuration
//@EnableElasticsearchRepositories(basePackages = "com.ftn.es.es")
public class Config extends AbstractElasticsearchConfiguration {

    @Value("${elasticsearch.cluster.name:elasticsearch}")
    private String clusterName;

//    @Bean
//    RestHighLevelClient client() {
//        ClientConfiguration clientConfiguration=ClientConfiguration.builder()
//                .connectedTo("localhost:9200", "localhost:9201")
//                .build();
//
//        return RestClients.create(clientConfiguration).rest();
//    }

    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        final ClientConfiguration clientConfiguration=ClientConfiguration.builder()
                .connectedTo("localhost:9200", "localhost:9201")
                .build();

        return RestClients.create(clientConfiguration).rest();
    }

    @Override
    public ElasticsearchOperations elasticsearchOperations() {
        return super.elasticsearchOperations();
    }


}