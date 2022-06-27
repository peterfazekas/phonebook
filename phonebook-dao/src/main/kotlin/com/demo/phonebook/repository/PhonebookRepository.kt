package com.demo.phonebook.repository

import com.demo.phonebook.domain.BusinessCardEntity
import com.demo.phonebook.domain.PhoneNumberEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface BusinessCardRepository : JpaRepository<BusinessCardEntity, Long> {

    fun findByFirstname(firstname: String): MutableList<BusinessCardEntity>

    fun findByLastname(lastname: String): MutableList<BusinessCardEntity>

    @Query(
        value = "SELECT * FROM BUSINESS_CARD INNER JOIN PHONE_NUMBER ON BUSINESS_CARD.CARD_ID = PHONE_NUMBER.CARD_ID WHERE NUMBER LIKE ?;",
        nativeQuery = true)
    fun findByPhoneNumber(number: String): MutableList<BusinessCardEntity>

    @Query(
        value = "SELECT * FROM BUSINESS_CARD INNER JOIN PHONE_NUMBER ON BUSINESS_CARD.CARD_ID = PHONE_NUMBER.CARD_ID WHERE TYPE LIKE ?;",
        nativeQuery = true)
    fun findByType(type: String): MutableList<BusinessCardEntity>
}

interface PhoneNumberRepository : JpaRepository<PhoneNumberEntity, Long>
