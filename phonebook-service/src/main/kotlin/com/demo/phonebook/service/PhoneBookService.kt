package com.demo.phonebook.service

import com.demo.phonebook.domain.BusinessCard
import com.demo.phonebook.domain.PhoneNumberEntity
import com.demo.phonebook.exception.DifferentBusinessCardException
import com.demo.phonebook.exception.NoSuchBusinessCardException

class PhoneBookService(
    private val converter: BusinessCardEntityConverter,
    private val initDataFactory: InitDataFactory,
    private val daoService: DaoService
) {

    fun findAllBusinessCard(): List<BusinessCard> {
        return daoService.findAllBusinessCard().map { converter.convertToBusinessCard(it, phoneNumberEntities()) }
    }

    private fun phoneNumberEntities(): List<PhoneNumberEntity> =
        daoService.findAllPhoneNumber()

    fun findBusinessCardById(id: Long): List<BusinessCard> {
        val phoneNumbers = daoService.findPhoneNumberByCardId(id)
        val businessCardEntities = daoService.findBusinessCardById(id)
        if (businessCardEntities.size == 0) {
            throw NoSuchBusinessCardException("No card found with id $id")
        }
        return businessCardEntities.map { converter.convertToBusinessCard(it, phoneNumbers) }
    }

    fun findBusinessCardByName(name: String): List<BusinessCard> =
        daoService.findBusinessCardByName(name).map { converter.convertToBusinessCard(it, phoneNumberEntities()) }

    fun findBusinessCardByPhoneNumber(phoneNumber: String): List<BusinessCard> {
        val phoneNumbers = daoService.findBusinessCardByPhoneNumber(phoneNumber)
        if (phoneNumbers.size == 0) {
            throw NoSuchBusinessCardException("No card found with number $phoneNumber")
        }
        val id = phoneNumbers.first().cardId
        val businessCardEntities = daoService.findBusinessCardById(id)
        if (businessCardEntities.size == 0) {
            throw NoSuchBusinessCardException("No card found with id $id")
        }
        return businessCardEntities.map { converter.convertToBusinessCard(it, phoneNumbers) }
    }

    fun findBusinessCardByType(type: String): List<BusinessCard> {
        val phoneNumbers = daoService.findBusinessCardByType(type)
        if (phoneNumbers.size == 0) {
            throw NoSuchBusinessCardException("No card found with type $type")
        }
        val id = phoneNumbers.first().cardId
        val businessCardEntities = daoService.findBusinessCardById(id)
        if (businessCardEntities.size == 0) {
            throw NoSuchBusinessCardException("No card found with id $id")
        }
        return businessCardEntities.map { converter.convertToBusinessCard(it, phoneNumbers) }
    }

    fun deleteBusinessCardById(id: Long) {
        daoService.findBusinessCardById(id).map { daoService.deleteBusinessCardById(id) }
    }

    fun addBusinessCardBy(businessCard: BusinessCard): BusinessCard {
        val convertToBusinessCardEntity = converter.convertToBusinessCardEntity(businessCard)
        val convertToPhoneNumberEntity = converter.convertToPhoneNumberEntity(businessCard)
        val saveBusinessCard = daoService.saveBusinessCard(convertToBusinessCardEntity, convertToPhoneNumberEntity)
        return converter.convertToBusinessCard(saveBusinessCard, phoneNumberEntities())
    }

    fun updateBusinessCardBy(id: Long, businessCard: BusinessCard): BusinessCard {
        val findBusinessCardById = findBusinessCardById(id).first()
        if (findBusinessCardById.id != id) {
            throw DifferentBusinessCardException("Business id's are differs! Original id: $id, updated id: ${findBusinessCardById.id}")
        }
        val updatedBusinessCard = BusinessCard(id, businessCard.firstname, businessCard.lastname, businessCard.phoneNumbers)
        val updatedBusinessCardEntity = converter.convertToBusinessCardEntity(updatedBusinessCard)
        val convertToPhoneNumberEntity = converter.convertToPhoneNumberEntity(businessCard)
        val savedBusinessCard = daoService.saveBusinessCard(updatedBusinessCardEntity, convertToPhoneNumberEntity)
        return converter.convertToBusinessCard(savedBusinessCard, phoneNumberEntities())
    }

    fun createInitialData() {
        initDataFactory.createBusinessCards()
            .map { addBusinessCardBy(it) }
    }


}