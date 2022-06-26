package com.demo.phonebook.repository

import com.demo.phonebook.domain.BusinessCardEntity
import com.demo.phonebook.domain.PhoneNumberEntity
import org.springframework.data.jpa.repository.JpaRepository

interface BusinessCardRepository : JpaRepository<BusinessCardEntity, Long> {
    fun findByFirstname(firstname: String): MutableList<BusinessCardEntity>
    fun findByLastname(lastname: String): MutableList<BusinessCardEntity>
}

interface PhoneNumberRepository : JpaRepository<PhoneNumberEntity, Long> {
    fun findByType(type: String): List<PhoneNumberEntity>
    fun findByNumber(number: String): PhoneNumberEntity?

}

