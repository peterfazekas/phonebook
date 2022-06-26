package com.demo.phonebook.service

import com.demo.phonebook.domain.BusinessCard
import com.demo.phonebook.domain.PhoneNumber

class InitDataFactory {

    fun createBusinessCards(): List<BusinessCard> {
        return listOf(
            BusinessCard(id = 1, lastname = "Git", firstname = "Áron", phoneNumbers = listOf(
                PhoneNumber(type = "mobile", number = "+36304875632"),
                PhoneNumber(type = "home", number = "+3611234567")
                )),
            BusinessCard(id = 2, lastname = "Bekre", firstname = "Pál", phoneNumbers = listOf(
                PhoneNumber(type = "mobile", number = "+36705001245")
            )),
            BusinessCard(id = 3, lastname = "Szüret", firstname = "Elek", phoneNumbers = listOf(
                PhoneNumber(type = "mobile", number =  "+36206274152")
            ))
        )
    }
}