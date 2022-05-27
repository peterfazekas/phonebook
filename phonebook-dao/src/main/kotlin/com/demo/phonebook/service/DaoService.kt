package com.demo.phonebook.service

import com.demo.phonebook.domain.BusinessCardEntity
import com.demo.phonebook.repository.BusinessCardRepository
import java.util.Optional

class DaoService(private val businessCardRepository: BusinessCardRepository) {

    fun findAllBusinessCard(): List<BusinessCardEntity> =
        businessCardRepository.findAll()

    fun findBusinessCardById(id: Long): Optional<BusinessCardEntity> =
        businessCardRepository.findById(id)

    fun findBusinessCardByFirstName(firstname: String) =
        businessCardRepository.findByFirstname(firstname)

    fun findBusinessCardByLastName(lastname: String) =
        businessCardRepository.findByLastname(lastname)

    fun findBusinessCardByName(firstname: String, lastname: String) =
        businessCardRepository.findByFirstnameAndLastname(firstname, lastname)

    fun findBusinessCardByPhoneNumberCategory(phoneNumber: String) =
        businessCardRepository.findByPhoneNumber(phoneNumber)

    fun saveBusinessCard(businessCardEntity: BusinessCardEntity) {
        businessCardRepository.save(businessCardEntity)
    }

    fun deleteBusinessCardById(id: Long) =
        businessCardRepository.deleteById(id)
}