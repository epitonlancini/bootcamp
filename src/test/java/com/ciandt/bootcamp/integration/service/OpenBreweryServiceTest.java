package com.ciandt.bootcamp.integration.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.ciandt.bootcamp.integration.dto.OpenBreweryResponse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;

@WebMvcTest(OpenBreweryService.class)
@Import(OpenBreweryService.class)
public class OpenBreweryServiceTest {

        @Autowired
        private OpenBreweryService openBreweryService;

        @Test
        public void shouldGetAllSuccess() throws Exception {
                List<OpenBreweryResponse> response = openBreweryService.getAll();
                assertEquals(response.size(), 20);
        }

        @Test
        public void shouldGetByIdSuccess() throws Exception {
                OpenBreweryResponse result = new OpenBreweryResponse();
                result.setId(2L);
                result.setName("Avondale Brewing Co");
                result.setBreweryType("micro");
                result.setStreet("201 41st St S");
                result.setCity("Birmingham");
                result.setState("Alabama");
                result.setCountry("United States");
                result.setLongitude("-86.774322");
                result.setLatitude("33.524521");
                result.setWebsiteUrl("http://www.avondalebrewing.com");
                OpenBreweryResponse response = openBreweryService.getById(2L);
                assertEquals(response, result);
        }

        @Test
        public void shouldGetByIdFail() throws Exception {
                OpenBreweryResponse response = openBreweryService.getById(-111L);
                assertEquals(response, null);
        }
}
