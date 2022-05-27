package com.demo.phonebook.service

import com.demo.phonebook.domain.BusinessCard
import com.demo.phonebook.domain.BusinessCardEntity

class BusinessCardEntityConverter {

    fun convertToBusinessCardEntity(businessCard : BusinessCard) =
        BusinessCardEntity(
            businessCard.id,
            businessCard.firstname,
            businessCard.lastname,
            businessCard.phoneNumber)

    fun convertToBusinessCard(businessCardEntity : BusinessCardEntity) =
        BusinessCard(
            businessCardEntity.id,
            businessCardEntity.firstname,
            businessCardEntity.lastname,
            businessCardEntity.phoneNumber)
}