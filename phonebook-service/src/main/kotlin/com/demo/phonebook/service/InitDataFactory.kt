package com.demo.phonebook.service

import com.demo.phonebook.domain.BusinessCard

class InitDataFactory {

    fun createBusinessCards(): List<BusinessCard> {
        return listOf(
            BusinessCard(lastname = "Git", firstname = "Áron", phoneNumber = "+36304875632"),
            BusinessCard(lastname = "Bekre", firstname = "Pál", phoneNumber = "+36705001245"),
            BusinessCard(lastname = "Szüret", firstname = "Elek", phoneNumber = "+36206274152")
        )
    }
}