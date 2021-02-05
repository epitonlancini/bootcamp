package com.ciandt.bootcamp.controller;

import com.ciandt.bootcamp.model.api.RateBreweryRequest;
import com.ciandt.bootcamp.service.BreweryService;
import com.google.gson.Gson;
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
    public void firstTest() throws Exception{

        RateBreweryRequest request = new RateBreweryRequest();
        request.setBreweryId(1L);
        request.setEmail("aaaaa");
        request.setRate(1);

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

}
