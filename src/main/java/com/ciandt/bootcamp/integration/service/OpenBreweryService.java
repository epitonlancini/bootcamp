package com.ciandt.bootcamp.integration.service;

import com.ciandt.bootcamp.integration.dto.OpenBreweryResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class OpenBreweryService {

    public List<OpenBreweryResponse> getAll() {
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://api.openbrewerydb.org/breweries";//TODO: conf
        ResponseEntity<OpenBreweryResponse[]> response = restTemplate.getForEntity(url, OpenBreweryResponse[].class);

        OpenBreweryResponse[] breweries = response.getBody();

        return Arrays.asList(breweries);
    }
}
