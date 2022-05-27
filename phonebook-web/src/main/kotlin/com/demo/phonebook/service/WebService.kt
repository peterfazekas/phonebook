package com.demo.phonebook.service

import com.demo.phonebook.domain.BusinessCard
import com.demo.phonebook.domain.WebBusinessCard
import java.util.*

class WebService(
    private val converter: BusinessCardConverter,
    private val phoneBookService: PhoneBookService) {

    fun findAllBusinessCard(): List<WebBusinessCard> {
        return phoneBookService.findAllBusinessCard().map { converter.convertToWebBusinessCard(it) }
    }

    fun findBusinessCardById(id :Long): WebBusinessCard =
        converter.convertToWebBusinessCard(phoneBookService.findBusinessCardById(id))

    fun createInitialData() = phoneBookService.createInitialData()

    fun deleteBusinessCardById(id : Long) =
        phoneBookService.deleteBusinessCardById(id)

}