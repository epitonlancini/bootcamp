package com.ciandt.bootcamp.model.api;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class RateBreweryRequest {

    @NotNull(message = "Brewery id is required")
    private Long breweryId;

    @NotEmpty(message = "Email is required")
    private String email;

    @NotNull(message = "Rate is required")
    private Integer rate;

}
