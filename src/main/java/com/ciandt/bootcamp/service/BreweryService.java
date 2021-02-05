package com.ciandt.bootcamp.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ciandt.bootcamp.integration.dto.OpenBreweryResponse;
import com.ciandt.bootcamp.integration.service.OpenBreweryService;
import com.ciandt.bootcamp.model.api.GetBreweryResponse;
import com.ciandt.bootcamp.model.api.RateBreweryRequest;
import com.ciandt.bootcamp.model.entity.BreweryRate;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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

            result = openBreweryResponseList.stream().map(brewery -> modelMapper.map(brewery, GetBreweryResponse.class))
                    .collect(Collectors.toList());

            if (result != null) {
                Map<Long, Double> averageRateMap = calculateAverageRate(
                        result.stream().map(GetBreweryResponse::getId).collect(Collectors.toList()));
                for (GetBreweryResponse r : result) {
                    Double averageRateValue = averageRateMap.get(r.getId());
                    r.setMediumRate(averageRateValue);
                }
            }
        }

        return result;
    }

    public ResponseEntity<String> rateBrewery(RateBreweryRequest rateBreweryRequest) {

        OpenBreweryResponse openBreweryResponse = openBreweryService.getById(rateBreweryRequest.getBreweryId());
        if (openBreweryResponse == null) {
            return new ResponseEntity<>("Brewery not found", HttpStatus.NOT_FOUND);
        }

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

    private Map<Long, Double> calculateAverageRate(List<Long> breweryIdList) {

        MatchOperation matchStage = Aggregation.match(new Criteria("breweryId").in(breweryIdList));

        GroupOperation groupOperation = Aggregation.group("breweryId").avg("rate").as("rateAvg");

        ProjectionOperation projectStage = Aggregation.project("breweryId", "rateAvg");

        Aggregation aggregation = Aggregation.newAggregation(matchStage, groupOperation, projectStage);

        AggregationResults<AverageRate> output = mongoTemplate.aggregate(aggregation, BreweryRate.class,
                AverageRate.class);

        List<AverageRate> averageRateList = output.getMappedResults();

        Map<Long, Double> averageRateMap = new HashMap<>();

        for (AverageRate rate : averageRateList) {
            averageRateMap.put(rate.getBreweryId(), rate.getRateAvg());
        }

        return averageRateMap;
    }

    @Data
    @NoArgsConstructor
    private static class AverageRate {

        @Id
        private Long breweryId;

        private Double rateAvg;

    }

}
