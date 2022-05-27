package com.demo.phonebook.domain

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

@Schema(name = "BusinessCard")
data class WebBusinessCard(

    @Positive
    val id : Long = 0,

    @NotNull
    @Schema(description = "The firstname of the given person", example = "Harry")
    val firstname : String,

    @NotNull
    @Schema(description = "The lastname of the given person", example = "Potter")
    val lastname : String,

    @NotNull
    @Schema(description = "Phone number", example = "+36301234567")
    val phoneNumber : String)