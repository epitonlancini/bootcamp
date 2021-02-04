package com.ciandt.bootcamp.model.api;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class RateBreweryRequest {

    @NotNull
    private Long breweryId;

    @NotEmpty
    private String email;

    private Integer rate;

}
