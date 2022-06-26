package com.demo.phonebook.domain

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Positive
import javax.validation.constraints.Size

@Schema(name = "PhoneNumber")
data class WebPhoneNumber(

    @Positive
    val id: Long = 0,

    @NotNull
    @Size(min = 2, max = 30)
    @Schema(description = "The type of the phone number", example = "mobile", minLength = 2, maxLength = 30)
    val type: String,

    @NotNull
    @Size(min = 10, max = 12)
    @Pattern(regexp="^+\\d{12}")
    @Schema(description = "the phone number", example = "+36301234567", minLength = 10, maxLength = 12, pattern = "^+\\d{12}")
    val number: String)
