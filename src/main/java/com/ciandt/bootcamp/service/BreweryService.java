package com.ciandt.bootcamp.service;

import com.ciandt.bootcamp.model.api.GetBreweryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BreweryService {

    public List<GetBreweryResponse> findAll(){

        List<GetBreweryResponse> teste = new ArrayList<>();

        teste.add(GetBreweryResponse.builder().id(1l).build());

        return teste;
    }

}
