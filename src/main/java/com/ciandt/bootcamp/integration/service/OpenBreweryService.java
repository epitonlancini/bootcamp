package com.ciandt.bootcamp.integration.service;

import com.ciandt.bootcamp.integration.dto.OpenBreweryResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class OpenBreweryService {

    // TODO: conf
    private static String URL = "https://api.openbrewerydb.org/breweries";

    public List<OpenBreweryResponse> getAll() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<OpenBreweryResponse[]> response = restTemplate.getForEntity(URL, OpenBreweryResponse[].class);

        OpenBreweryResponse[] breweries = response.getBody();

        return Arrays.asList(breweries);
    }

    public OpenBreweryResponse getById(Long id) {
        try {
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<OpenBreweryResponse> response = restTemplate.getForEntity(URL + "/" + id,
                    OpenBreweryResponse.class);

            return response.getBody();
        } catch (HttpStatusCodeException e) {
            return null;
        }
    }
}
