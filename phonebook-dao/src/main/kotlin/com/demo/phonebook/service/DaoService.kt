package com.demo.phonebook.service

import com.demo.phonebook.domain.BusinessCardEntity
import com.demo.phonebook.repository.BusinessCardRepository
import java.util.Optional

class DaoService(private val businessCardRepository: BusinessCardRepository) {

    fun findAllBusinessCard(): List<BusinessCardEntity> =
        businessCardRepository.findAll()

    fun findBusinessCardById(id: Long): Optional<BusinessCardEntity> =
        businessCardRepository.findById(id)

    fun findBusinessCardByName(name: String): MutableList<BusinessCardEntity> {
        val findByName = businessCardRepository.findByFirstname(name)
        findByName.addAll(businessCardRepository.findByLastname(name))
        return findByName
    }

    fun findBusinessCardByPhoneNumber(phoneNumber: String) =
        businessCardRepository.findByPhoneNumber(phoneNumber)

    fun saveBusinessCard(businessCardEntity: BusinessCardEntity): BusinessCardEntity =
        businessCardRepository.save(businessCardEntity)

    fun deleteBusinessCardById(id: Long) =
        businessCardRepository.deleteById(id)
}