package com.ciandt.bootcamp.model.api;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class RateBreweryRequest {

    @Schema(description = "Brewery id.", example = "1", required = true)
    @NotNull(message = "Brewery id is required")
    private Long breweryId;

    @Schema(description = "User email.", example = "teste@email.com", required = true)
    @NotEmpty(message = "Email is required")
    @Email
    private String email;

    @Schema(description = "Brewery rate.", example = "4", required = true)
    @NotNull(message = "Rate is required")
    private Integer rate;

}
