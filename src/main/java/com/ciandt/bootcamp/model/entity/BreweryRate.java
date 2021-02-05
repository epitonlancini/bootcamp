package com.ciandt.bootcamp.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Document
@Data
@NoArgsConstructor
@CompoundIndexes({
        @CompoundIndex(def = "{ 'email': 1, 'breweryId': 1 }", unique = true)
}
)
public class BreweryRate {

    @Id
    private ObjectId id;

    @NotNull
    private Long breweryId;

    @NotEmpty
    private String email;

    private Integer rate;

}
