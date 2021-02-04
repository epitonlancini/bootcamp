package com.ciandt.bootcamp.model.api;

import lombok.*;

@Data
@Builder
public class GetBreweryResponse {

    private Long id;

    private String name;

    private String breweryType;

    private String street;

    private String city;

    private String state;

    private String country;

    private Long longitude;

    private Long latitude;

    private String websiteUrl;

    private Integer mediumRate;


}
