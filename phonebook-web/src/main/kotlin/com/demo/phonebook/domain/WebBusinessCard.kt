package com.demo.phonebook.domain

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Positive
import javax.validation.constraints.Size

@Schema(name = "BusinessCard")
data class WebBusinessCard(

    @Positive
    val id : Long = 0,

    @NotNull
    @Size(min = 2, max = 30)
    @Schema(description = "The firstname of the given person", example = "Harry", minLength = 2, maxLength = 30)
    val firstname : String,

    @NotNull
    @Size(min = 2, max = 30)
    @Schema(description = "The lastname of the given person", example = "Potter", minLength = 2, maxLength = 30)
    val lastname : String,

    @NotNull
    @Pattern(regexp="^+\\d{12}")
    @Schema(description = "Phone number", example = "+36301234567", pattern = "^+\\d{12}")
    val phoneNumber : String)