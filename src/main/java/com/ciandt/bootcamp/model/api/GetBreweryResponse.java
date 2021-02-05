package com.ciandt.bootcamp.model.api;

import lombok.*;

@Data
@NoArgsConstructor
public class GetBreweryResponse {

    private Long id;

    private String name;

    private String breweryType;

    private String street;

    private String city;

    private String state;

    private String country;

    private String longitude;

    private String latitude;

    private String websiteUrl;

    private Double mediumRate;


}
