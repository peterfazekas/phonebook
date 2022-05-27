package com.demo.phonebook.service

import com.demo.phonebook.domain.BusinessCard
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

    fun createInitialData() {
        initDataFactory.createBusinessCards()
            .map { converter.convertToBusinessCardEntity(it) }
            .map { daoService.saveBusinessCard(it) }
    }

    fun deleteBusinessCardById(id: Long) {
        daoService.findBusinessCardById(id).map { daoService.deleteBusinessCardById(id) }
            .orElseThrow { NoSuchBusinessCardException("No card found with id $id") }
    }


}