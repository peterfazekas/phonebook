package com.demo.phonebook.service

import com.demo.phonebook.domain.BusinessCard
import com.demo.phonebook.domain.BusinessCardEntity
import com.demo.phonebook.domain.PhoneNumber
import com.demo.phonebook.domain.PhoneNumberEntity

class BusinessCardEntityConverter {

    fun convertToBusinessCardEntity(businessCard : BusinessCard) : BusinessCardEntity{
        val businessCardEntity = BusinessCardEntity(
            businessCard.id,
            businessCard.firstname,
            businessCard.lastname
        )
        businessCardEntity.phoneNumbers = convertToPhoneNumberEntity(businessCard.phoneNumbers, businessCardEntity)
        return businessCardEntity
    }

    private fun convertToPhoneNumberEntity(phoneNumbers: List<PhoneNumber>, businessCardEntity: BusinessCardEntity) =
        phoneNumbers.map { PhoneNumberEntity(it.id, it.type, it.number, businessCardEntity) }


    fun convertToBusinessCard(businessCardEntity : BusinessCardEntity) =
        BusinessCard(
            businessCardEntity.id,
            businessCardEntity.firstname,
            businessCardEntity.lastname,
            convertToPhoneNumber(businessCardEntity.phoneNumbers))

    private fun convertToPhoneNumber(phoneNumberEntities: List<PhoneNumberEntity>) =
        phoneNumberEntities.map { PhoneNumber(it.id, it.type, it.number) }

}