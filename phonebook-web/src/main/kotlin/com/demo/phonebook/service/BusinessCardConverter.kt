package com.demo.phonebook.service

import com.demo.phonebook.domain.BusinessCard
import com.demo.phonebook.domain.PhoneNumber
import com.demo.phonebook.domain.WebBusinessCard
import com.demo.phonebook.domain.WebPhoneNumber

class BusinessCardConverter {

    fun convertToWebBusinessCard(businessCard : BusinessCard) =
        WebBusinessCard(
            businessCard.id,
            businessCard.firstname,
            businessCard.lastname,
            convertToWebPhoneNumber(businessCard.phoneNumbers))

    private fun convertToWebPhoneNumber(phoneNumbers: List<PhoneNumber>) =
        phoneNumbers.map { WebPhoneNumber(it.id, it.type, it.number) }

    fun convertToBusinessCard(webBusinessCard : WebBusinessCard) =
        BusinessCard(
            webBusinessCard.id,
            webBusinessCard.firstname,
            webBusinessCard.lastname,
            convertToPhoneNumber(webBusinessCard.phoneNumbers))

    private fun convertToPhoneNumber(webPhoneNumbers: List<WebPhoneNumber>) =
        webPhoneNumbers.map { PhoneNumber(it.id, it.type, it.number) }

}