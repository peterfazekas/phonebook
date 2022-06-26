package com.demo.phonebook.domain

data class PhoneNumber(
    val id : Long = 0,
    val type : String,
    val number : String)