package com.ciandt.bootcamp.service;

import com.ciandt.bootcamp.conf.MongoTestConfig;
import com.ciandt.bootcamp.model.entity.BreweryRate;
import com.ciandt.bootcamp.repository.BreweryRateRepository;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;
import org.jeasy.random.randomizers.EmailRandomizer;
import org.jeasy.random.randomizers.range.IntegerRangeRandomizer;
import org.jeasy.random.randomizers.range.LongRangeRandomizer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {MongoTestConfig.class})
@AutoConfigureDataMongo
@Slf4j
public class BreweryServiceTest {

    @Autowired
    private BreweryRateRepository breweryRateRepository;

    @Autowired
    private BreweryService breweryService;

    @Test
    public void shouldSucessfullyCalculateAverage() {
        EasyRandomParameters parameters = new EasyRandomParameters();

        parameters.randomize(FieldPredicates.named("breweryId")
                .and(FieldPredicates.inClass(BreweryRate.class)), new LongRangeRandomizer(1l, 1000L));
        parameters.randomize(FieldPredicates.named("rate")
                .and(FieldPredicates.inClass(BreweryRate.class)), new IntegerRangeRandomizer(1, 5));
        parameters.randomize(FieldPredicates.named("email")
                .and(FieldPredicates.inClass(BreweryRate.class)), new EmailRandomizer());

        EasyRandom easyRandom = new EasyRandom(parameters);

        easyRandom.nextObject(BreweryRate.class);

        List<BreweryRate> rates = easyRandom.objects(BreweryRate.class, new IntegerRangeRandomizer(1, 5000).getRandomValue()).collect(Collectors.toList());

        breweryRateRepository.saveAll(rates);

        List<BreweryRate> persistedRates = new ArrayList<>();

        breweryRateRepository.findAll().iterator().forEachRemaining(persistedRates::add);

        Map<Long, Double> average = persistedRates.stream().collect(Collectors.groupingBy(BreweryRate::getBreweryId, Collectors.averagingInt(BreweryRate::getRate)));

        List<Long> breweryIds = persistedRates.stream().map(BreweryRate::getBreweryId).collect(Collectors.toList());

        //Calculate Average Rate
        Map<Long, Double> calculatedAverageRate = breweryService.calculateAverageRate(breweryIds);

        assertEquals(average, calculatedAverageRate);

    }

}

