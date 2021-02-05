package com.ciandt.bootcamp.controller;

import com.ciandt.bootcamp.model.api.GetBreweryResponse;
import com.ciandt.bootcamp.service.BreweryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/brewery")
@Tag(name = "Brewery", description = "The Brewery API")
public class BreweryController {

    @Autowired
    private BreweryService breweryService;

    @Operation(summary = "Get the breweries")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Sucessfully get breweries") })
    @GetMapping(value = "/getBreweries", produces = { "application/json" })
    public List<GetBreweryResponse> findAll() {
        return this.breweryService.findAll();
    }

}
