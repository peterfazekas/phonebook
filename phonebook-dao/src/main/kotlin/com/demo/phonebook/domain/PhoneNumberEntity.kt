package com.demo.phonebook.domain

data class PhoneNumberEntity(
    val id : Long,
    val type : String,
    val number : String,
    val cardId : Long)