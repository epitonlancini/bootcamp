package com.ciandt.bootcamp.controller;

import com.ciandt.bootcamp.model.api.RateBreweryRequest;
import com.ciandt.bootcamp.service.BreweryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BreweryRateController {

  @Autowired private BreweryService breweryService;

  @PostMapping("/breweryRate")
  public void rateBrewery(@RequestBody RateBreweryRequest request) {
    breweryService.rateBrewery(request);
  }
}
