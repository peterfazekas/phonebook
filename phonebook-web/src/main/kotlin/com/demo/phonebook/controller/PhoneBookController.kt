package com.demo.phonebook.controller

import com.demo.phonebook.domain.SuccessResponse
import com.demo.phonebook.domain.WebBusinessCard
import com.demo.phonebook.service.WebService
import io.swagger.v3.oas.annotations.Operation
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@Validated
class PhoneBookController {

    private val logger: Logger = LoggerFactory.getLogger(PhoneBookController::class.java)

    @Autowired
    lateinit var service: WebService

    @GetMapping("")
    @Operation(summary = "List all business cards")
    fun getAllBusinessCard(): List<WebBusinessCard> {
        logger.info("GET / endpoint called")
        return service.findAllBusinessCard()
    }

    @GetMapping("/{id}")
    @Operation(summary = "List business card by id")
    fun getBusinessCardById(@PathVariable id: Long): WebBusinessCard {
        logger.info("GET /$id endpoint called")
        return service.findBusinessCardById(id)
    }

    @PatchMapping("init")
    @Operation(summary = "Creates initial data")
    fun createInitialData(): SuccessResponse {
        logger.info("PATCH /init endpoint called")
        service.createInitialData()
        return SuccessResponse("Data created")
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "List business cards by name")
    fun getBusinessCardByName(@PathVariable name: String): List<WebBusinessCard> {
        logger.info("GET /name/$name endpoint called")
        return service.findBusinessCardByName(name)
    }

    @GetMapping("/number/{number}")
    @Operation(summary = "List business cards by phone number")
    fun getBusinessCardByNumber(@PathVariable number: String): WebBusinessCard? {
        logger.info("GET /number/$number endpoint called")
        return service.findBusinessCardByNumber(number)
    }

    @GetMapping("/type/{type}")
    @Operation(summary = "List business cards by phone number type")
    fun getBusinessCardByType(@PathVariable type: String): List<WebBusinessCard> {
        logger.info("GET /number/$type endpoint called")
        return service.findBusinessCardByByType(type)
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete business card by id")
    fun deleteBusinessCard(@PathVariable id: Long): SuccessResponse {
        logger.info("DELETE /delete/$id endpoint called")
        service.deleteBusinessCardById(id)
        return SuccessResponse("Business card deleted with id: $id")
    }

    @PostMapping("/add")
    @Operation(summary = "Add new business card to the phonebook")
    fun addNewBusinessCard(@Valid @RequestBody webBusinessCard: WebBusinessCard): WebBusinessCard {
        logger.info("POST /add endpoint called with payload: $webBusinessCard")
        return service.addBusinessCard(webBusinessCard)
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update business card to the phonebook")
    fun updateBusinessCard(@PathVariable id : Long, @Valid @RequestBody webBusinessCard: WebBusinessCard): WebBusinessCard {
        logger.info("PUT /update/{id} endpoint called with id: $id and payload: $webBusinessCard")
        return service.updateBusinessCard(id, webBusinessCard)
    }

}