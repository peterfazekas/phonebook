package com.demo.phonebook.service

import com.demo.phonebook.domain.BusinessCardEntity
import com.demo.phonebook.repository.BusinessCardRepository
import com.demo.phonebook.repository.PhoneNumberRepository
import java.util.Optional


class DaoService(
    private val businessCardRepository: BusinessCardRepository,
    private val phoneNumberRepository: PhoneNumberRepository
) {
    fun findAllBusinessCard(): List<BusinessCardEntity> =
        businessCardRepository.findAll()

    fun findBusinessCardById(id: Long): Optional<BusinessCardEntity> =
        businessCardRepository.findById(id)

    fun findBusinessCardByName(name: String): MutableList<BusinessCardEntity> {
        val findByName = businessCardRepository.findByFirstname(name)
        findByName.addAll(businessCardRepository.findByLastname(name))
        return findByName
    }

    fun findBusinessCardByPhoneNumber(phoneNumber: String): MutableList<BusinessCardEntity> =
        businessCardRepository.findByPhoneNumber(phoneNumber)

    fun findBusinessCardByType(type: String): MutableList<BusinessCardEntity> =
        businessCardRepository.findByType(type)

    fun saveBusinessCard(businessCardEntity: BusinessCardEntity): BusinessCardEntity {
        businessCardRepository.save(businessCardEntity)
        businessCardEntity.phoneNumbers.forEach { phoneNumberRepository.save(it) }
        return businessCardEntity
    }

    fun deleteBusinessCardById(id: Long) =
        businessCardRepository.deleteById(id)
}