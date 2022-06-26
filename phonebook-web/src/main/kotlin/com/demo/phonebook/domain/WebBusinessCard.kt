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
    @Schema(description = "List of phone numbers")
    val phoneNumbers : List<WebPhoneNumber>)