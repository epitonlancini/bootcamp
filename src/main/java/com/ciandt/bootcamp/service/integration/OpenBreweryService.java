package com.ciandt.bootcamp.service.integration;

import com.ciandt.bootcamp.model.api.GetBreweryResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class OpenBreweryService {


    public List<GetBreweryResponse> getAll(){
        RestTemplate restTemplate = new RestTemplate();

        return Collections.emptyList();
    }
}
