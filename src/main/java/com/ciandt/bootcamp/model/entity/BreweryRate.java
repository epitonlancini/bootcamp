package com.ciandt.bootcamp.model.entity;

import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Document
@Data
public class BreweryRate {

    @Id
    private ObjectId id;

    @NotNull
    private Long breweryId;

    @NotEmpty
    private String email;

    private Integer rate;

}
