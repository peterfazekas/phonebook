package com.demo.phonebook.domain

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

@Schema(name = "PhoneNumber")
data class WebPhoneNumber(

    @Positive
    val id : Long = 0,

    @NotNull
    @Schema(description = "The name of the phone number category", example = "mobile")
    val name : String,

    @NotNull
    @Schema(description = "The phone number. Must contains numbers (0..9) and plus sign '+' only!", example = "+36301234567")
    val number: String)