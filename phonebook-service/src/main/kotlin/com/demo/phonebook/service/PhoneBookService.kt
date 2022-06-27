package com.demo.phonebook.service

import com.demo.phonebook.domain.BusinessCard
import com.demo.phonebook.exception.DifferentBusinessCardException
import com.demo.phonebook.exception.NoSuchBusinessCardException

class PhoneBookService(
    private val converter: BusinessCardEntityConverter,
    private val initDataFactory: InitDataFactory,
    private val daoService: DaoService
) {

    fun findAllBusinessCard(): List<BusinessCard> =
        daoService.findAllBusinessCard().map { converter.convertToBusinessCard(it) }

    fun findBusinessCardById(id: Long): BusinessCard =
        daoService.findBusinessCardById(id).map { converter.convertToBusinessCard(it) }
            .orElseThrow { NoSuchBusinessCardException("No card found with id $id") }

    fun findBusinessCardByName(name: String): List<BusinessCard> =
        daoService.findBusinessCardByName(name).map { converter.convertToBusinessCard(it) }

    fun findBusinessCardByPhoneNumber(phoneNumber: String): List<BusinessCard> =
        daoService.findBusinessCardByPhoneNumber(phoneNumber).map { converter.convertToBusinessCard(it) }

    fun findBusinessCardByType(type: String): List<BusinessCard> =
        daoService.findBusinessCardByType(type).map { converter.convertToBusinessCard(it) }

    fun deleteBusinessCardById(id: Long) {
        daoService.findBusinessCardById(id).map { daoService.deleteBusinessCardById(id) }
            .orElseThrow { NoSuchBusinessCardException("No card found with id $id") }
    }

    fun addBusinessCardBy(businessCard: BusinessCard): BusinessCard {
        val newEntity = converter.convertToBusinessCardEntity(businessCard)
        val saveBusinessCard = daoService.saveBusinessCard(newEntity)

        return converter.convertToBusinessCard(saveBusinessCard)
    }

    fun updateBusinessCardBy(id: Long, businessCard: BusinessCard): BusinessCard {
        val findBusinessCardById = findBusinessCardById(id)
        if (findBusinessCardById.id != id) {
            throw DifferentBusinessCardException("Business id's are differs! Original id: $id, updated id: ${findBusinessCardById.id}")
        }
        val updatedBusinessCard = BusinessCard(id, businessCard.firstname, businessCard.lastname, businessCard.phoneNumbers)
        val updatedBusinessCardEntity = converter.convertToBusinessCardEntity(updatedBusinessCard)
        val savedBusinessCard = daoService.saveBusinessCard(updatedBusinessCardEntity)
        return converter.convertToBusinessCard(savedBusinessCard)
    }

    fun createInitialData() {
        initDataFactory.createBusinessCards()
            .map { converter.convertToBusinessCardEntity(it) }
            .map { daoService.saveBusinessCard(it) }
    }


}