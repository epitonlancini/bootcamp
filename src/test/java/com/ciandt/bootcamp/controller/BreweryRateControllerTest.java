package com.ciandt.bootcamp.controller;

import com.ciandt.bootcamp.model.api.RateBreweryRequest;
import com.ciandt.bootcamp.service.BreweryService;
import com.google.gson.Gson;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;
import org.jeasy.random.randomizers.EmailRandomizer;
import org.jeasy.random.randomizers.number.IntegerRandomizer;
import org.jeasy.random.randomizers.range.IntegerRangeRandomizer;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {com.ciandt.bootcamp.service.BreweryService.class})
@WebMvcTest(BreweryRateController.class)
@Import(BreweryRateController.class)
public class BreweryRateControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BreweryService breweryService;

    @Test
    public void shouldSuccess() throws Exception{

        EasyRandomParameters parameters = new EasyRandomParameters();

        parameters.randomize(FieldPredicates.named("rate")
                .and(FieldPredicates.inClass(RateBreweryRequest.class)), new IntegerRangeRandomizer(1,5));
        parameters.randomize(FieldPredicates.named("email")
                .and(FieldPredicates.inClass(RateBreweryRequest.class)), new EmailRandomizer());

        EasyRandom easyRandom = new EasyRandom(parameters);

        RateBreweryRequest request = easyRandom.nextObject(RateBreweryRequest.class);

        Mockito.when(breweryService.rateBrewery(request)).thenReturn(ResponseEntity.ok(null));

        Gson gson = new Gson();
        String json = gson.toJson(request, RateBreweryRequest.class);

        this.mockMvc.perform(
                post("/breweryRate")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldFailInvalidEmail() throws Exception{

    }

    @Test
    public void shouldFailInvalidRate() throws Exception{

    }

    @Test
    public void shouldFailInvalidBreweryId() throws Exception{

    }

}
