package com.ciandt.bootcamp.service;

import com.ciandt.bootcamp.model.api.GetBreweryResponse;
import com.ciandt.bootcamp.model.api.RateBreweryRequest;
import com.ciandt.bootcamp.model.entity.BreweryRate;
import com.ciandt.bootcamp.integration.dto.OpenBreweryResponse;
import com.ciandt.bootcamp.integration.service.OpenBreweryService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BreweryService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private OpenBreweryService openBreweryService;

    public List<GetBreweryResponse> findAll() {

        List<GetBreweryResponse> result = Collections.emptyList();

        List<OpenBreweryResponse> openBreweryResponseList = openBreweryService.getAll();

        if (openBreweryResponseList != null) {

            ModelMapper modelMapper = new ModelMapper();


            result = openBreweryResponseList.stream().map(brewery -> modelMapper.map(brewery, GetBreweryResponse.class)).collect(Collectors.toList());

            //TODO: get medium rate
        }

        return result;
    }

    public ResponseEntity<String> rateBrewery(RateBreweryRequest rateBreweryRequest) {
        int rate = rateBreweryRequest.getRate();
        if (rate < 0 || rate > 5) {
            return new ResponseEntity<>("Rate need to be between 0 and 5", HttpStatus.BAD_REQUEST);
        }

        Query query = new Query();
        query.addCriteria(Criteria.where("breweryId").is(rateBreweryRequest.getBreweryId()).and("email")
                .is(rateBreweryRequest.getEmail()));

        Update update = new Update();
        update.set("rate", rateBreweryRequest.getRate());

        mongoTemplate.upsert(query, update, BreweryRate.class);

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
