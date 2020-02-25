package com.ftn.es.repositoryes;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.ftn.es.model.UserES;


@Repository
public interface UserESRepository extends ElasticsearchRepository <UserES, Long> {
}
