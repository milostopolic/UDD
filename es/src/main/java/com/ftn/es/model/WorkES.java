package com.ftn.es.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(indexName = "es",type = "work")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkES {

    @Id
    private Long id;

    @Field(type = FieldType.Text, searchAnalyzer = "serbian",analyzer = "serbian")
    private String title;

    @Field(type = FieldType.Text, searchAnalyzer = "serbian", analyzer = "serbian")
    private String keyTerms;

    @Field(type=FieldType.Text)
    private String authors;

    @Field(type=FieldType.Text)
    private String workAbstract;

    @Field(type = FieldType.Text, searchAnalyzer = "serbian", analyzer = "serbian")
    private String workContent;

    @Field(type=FieldType.Long)
    private Long scienceArea;

    @Field(type=FieldType.Text, searchAnalyzer = "serbian", analyzer = "serbian")
    private String magazineName;

    @Field(type = FieldType.Text)
    private String status;

}
