package com.demo.phonebook.repository

import com.demo.phonebook.domain.BusinessCardEntity
import org.springframework.data.jpa.repository.JpaRepository

interface BusinessCardRepository : JpaRepository<BusinessCardEntity, Long> {
    fun findByFirstname(firstname: String): MutableList<BusinessCardEntity>
    fun findByLastname(lastname: String): MutableList<BusinessCardEntity>
    fun findByPhoneNumber(phoneNumber: String): BusinessCardEntity?
}

