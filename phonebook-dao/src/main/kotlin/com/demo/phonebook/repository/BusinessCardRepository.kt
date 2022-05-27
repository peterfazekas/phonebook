package com.demo.phonebook.repository

import com.demo.phonebook.domain.BusinessCardEntity
import org.springframework.data.jpa.repository.JpaRepository

interface BusinessCardRepository : JpaRepository<BusinessCardEntity, Long> {
    fun findByFirstname(firstname : String) : List<BusinessCardEntity>
    fun findByLastname(lastname : String) : List<BusinessCardEntity>
    fun findByFirstnameAndLastname(firstname : String, lastname : String) : List<BusinessCardEntity>
    fun findByPhoneNumber(phoneNumber : String) : BusinessCardEntity?
}

