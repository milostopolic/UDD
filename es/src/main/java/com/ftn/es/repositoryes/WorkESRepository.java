package com.ftn.es.repositoryes;

import java.util.List;

import org.elasticsearch.search.SearchHits;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.ftn.es.model.WorkES;

@Repository
public interface WorkESRepository extends ElasticsearchRepository <WorkES,Long> {

    @Query(
                "{\"match_phrase\" : " +
                    "{\"title\" : " +
                        "{\"query\" : \"?0\"," +
                        "\"analyzer\":\"serbian\"}}}")
    List<WorkES> findByTitle(String title);

    @Query("{\"match\" : " +
            "{\"title\" : " +
            "{\"query\" : \"?0\","+
            "\"analyzer\":\"serbian\"}}},"
            +"\"highlight\" :"
            +"{\"fields\" : "
            +"{\"title\" : "
            +"{\"type\":\"plain\"}}}"
            )
    SearchHits findByTitle1(String title);

    List<WorkES> findAllByTitleLike(String title);
    //List<WorkES> findAllBy();

    //filtriranje po naucnim oblastima

    List<WorkES> findByScienceAreaIn(List<Long> areas);

    WorkES findById(long id);
}