package com.demo.phonebook.service

import com.demo.phonebook.domain.BusinessCard
import com.demo.phonebook.domain.BusinessCardEntity
import com.demo.phonebook.domain.PhoneNumber
import com.demo.phonebook.domain.PhoneNumberEntity

class BusinessCardEntityConverter {

    fun convertToBusinessCardEntity(businessCard: BusinessCard) =
        BusinessCardEntity(
            businessCard.id,
            businessCard.firstname,
            businessCard.lastname)

    fun convertToPhoneNumberEntity(businessCard: BusinessCard) =
        businessCard.phoneNumbers.map { PhoneNumberEntity(it.id, it.type, it.number, businessCard.id) }


    fun convertToBusinessCard(businessCardEntity: BusinessCardEntity, phoneNumberEntities: List<PhoneNumberEntity>) =
        BusinessCard(
            businessCardEntity.id,
            businessCardEntity.firstname,
            businessCardEntity.lastname,
            convertToPhoneNumber(businessCardEntity.id, phoneNumberEntities))

    private fun convertToPhoneNumber(cardId: Long, phoneNumberEntities: List<PhoneNumberEntity>) =
        phoneNumberEntities
            .filter { it.cardId == cardId }
            .map { PhoneNumber(it.id, it.type, it.number) }
}