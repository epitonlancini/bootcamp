package com.ciandt.bootcamp.controller;

import javax.validation.Valid;

import com.ciandt.bootcamp.model.api.RateBreweryRequest;
import com.ciandt.bootcamp.service.BreweryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Brewery Rate", description = "The Brewery Rate API")
public class BreweryRateController {

  @Autowired
  private BreweryService breweryService;

  @Operation(summary = "Save the brewery rate")
  @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Brewery rate created or updated"),
      @ApiResponse(responseCode = "400", description = "Invalid input"),
      @ApiResponse(responseCode = "404", description = "Brewery not found") })
  @PostMapping(value = "/breweryRate", produces = { "application/json" })
  public ResponseEntity<String> rateBrewery(@Valid @RequestBody RateBreweryRequest request) {
    return breweryService.rateBrewery(request);
  }
}
