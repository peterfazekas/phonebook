package com.demo.phonebook.domain

data class BusinessCard(
    val id : Long = 0,
    val firstname : String,
    val lastname : String,
    val phoneNumber : String)