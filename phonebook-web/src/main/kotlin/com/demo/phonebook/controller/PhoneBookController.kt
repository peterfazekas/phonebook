package com.demo.phonebook.controller

import com.demo.phonebook.domain.SuccessMessage
import com.demo.phonebook.domain.WebBusinessCard
import com.demo.phonebook.service.WebService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@Validated
class PhoneBookController {

    @Autowired
    lateinit var service : WebService

    @GetMapping("")
    fun getAllBusinessCard(): List<WebBusinessCard> =
        service.findAllBusinessCard()

    @GetMapping("{id}")
    fun getBusinessCardById(@PathVariable id : Long): WebBusinessCard =
        service.findBusinessCardById(id)

    @PatchMapping("init")
    fun createInitialData() : SuccessMessage {
        service.createInitialData()
        return SuccessMessage("Data created")
    }

    @DeleteMapping("{id}")
    fun deleteBusinessCard(@PathVariable id : Long) =
        service.deleteBusinessCardById(id)

}