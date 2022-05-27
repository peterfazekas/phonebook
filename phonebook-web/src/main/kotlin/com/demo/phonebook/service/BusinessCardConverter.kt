package com.demo.phonebook.service

import com.demo.phonebook.domain.BusinessCard
import com.demo.phonebook.domain.WebBusinessCard

class BusinessCardConverter {

    fun convertToWebBusinessCard(businessCard : BusinessCard) =
        WebBusinessCard(
            businessCard.id,
            businessCard.firstname,
            businessCard.lastname,
            businessCard.phoneNumber)

    fun convertToBusinessCard(webBusinessCard : WebBusinessCard) =
        BusinessCard(
            webBusinessCard.id,
            webBusinessCard.firstname,
            webBusinessCard.lastname,
            webBusinessCard.phoneNumber)
}