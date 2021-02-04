package com.ciandt.bootcamp.service;

import com.ciandt.bootcamp.model.api.GetBreweryResponse;
import com.ciandt.bootcamp.model.api.RateBreweryRequest;
import com.ciandt.bootcamp.model.entity.BreweryRate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BreweryService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<GetBreweryResponse> findAll(){

        List<GetBreweryResponse> teste = new ArrayList<>();

        teste.add(GetBreweryResponse.builder().id(1l).build());

        return teste;
    }

    public void rateBrewery(RateBreweryRequest rateBreweryRequest){

        Query query = new Query();
        query.addCriteria(Criteria
                .where("breweryId").is(rateBreweryRequest.getBreweryId())
                .and("email").is(rateBreweryRequest.getEmail()));

        Update update = new Update();
        update.set("rate", rateBreweryRequest.getRate());

        mongoTemplate.upsert(query, update, BreweryRate.class);

    }


}
