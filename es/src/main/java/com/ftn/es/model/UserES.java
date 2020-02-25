package com.ftn.es.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(indexName = "us",type = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserES {

    @Id
    private Long id;

    @Field(type= FieldType.Long)
    private Long reviewerId;

    @Field(type = FieldType.Text)
    private String name;

    @GeoPointField
    private GeoPoint location;

}
