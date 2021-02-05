package com.ciandt.bootcamp.integration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OpenBreweryResponse {

    private Long id;

    private String name;

    @JsonProperty("brewery_type")
    private String breweryType;

    private String street;

    private String city;

    private String state;

    private String country;

    private String longitude;

    private String latitude;

    @JsonProperty("website_url")
    private String websiteUrl;

}
