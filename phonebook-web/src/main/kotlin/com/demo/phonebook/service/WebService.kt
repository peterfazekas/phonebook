package com.demo.phonebook.service

import com.demo.phonebook.domain.WebBusinessCard

class WebService(
    private val converter: BusinessCardConverter,
    private val phoneBookService: PhoneBookService) {

    fun findAllBusinessCard(): List<WebBusinessCard> {
        return phoneBookService.findAllBusinessCard().map { converter.convertToWebBusinessCard(it) }
    }

    fun findBusinessCardById(id :Long): WebBusinessCard =
        converter.convertToWebBusinessCard(phoneBookService.findBusinessCardById(id))

    fun findBusinessCardByName(name: String): List<WebBusinessCard> =
        phoneBookService.findBusinessCardByName(name).map { converter.convertToWebBusinessCard(it) }

    fun findBusinessCardByNumber(number: String): List<WebBusinessCard> =
        phoneBookService.findBusinessCardByPhoneNumber(number).map { converter.convertToWebBusinessCard(it) }

    fun findBusinessCardByByType(type: String): List<WebBusinessCard> =
        phoneBookService.findBusinessCardByType(type).map { converter.convertToWebBusinessCard(it) }

    fun deleteBusinessCardById(id : Long) =
        phoneBookService.deleteBusinessCardById(id)

    fun addBusinessCard(webBusinessCard: WebBusinessCard): WebBusinessCard {
        val newBusinessCard = converter.convertToBusinessCard(webBusinessCard)
        val newWebBusinessCard = phoneBookService.addBusinessCardBy(newBusinessCard)
        return converter.convertToWebBusinessCard(newWebBusinessCard)
    }

    fun updateBusinessCard(id: Long, webBusinessCard: WebBusinessCard): WebBusinessCard {
        val businessCard = converter.convertToBusinessCard(webBusinessCard)
        val updatedWebBusinessCard = phoneBookService.updateBusinessCardBy(id, businessCard)
        return converter.convertToWebBusinessCard(updatedWebBusinessCard)
    }

    fun createInitialData() = phoneBookService.createInitialData()

}