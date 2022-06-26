package com.demo.phonebook.service

import com.demo.phonebook.domain.BusinessCardEntity
import org.springframework.jdbc.core.JdbcTemplate
import java.util.*

class DaoService(
    private val jdbcTemplate: JdbcTemplate,
    private val businessCardRowMapper: BusinessCardRowMapper,
    private val phoneNumberRowMapper: PhoneNumberRowMapper) {

    fun findAllBusinessCard(): List<BusinessCardEntity> =
        jdbcTemplate.query("SELECT * FROM EMPLOYEE", businessCardRowMapper)

    fun findBusinessCardById(id: Long): Optional<BusinessCardEntity> =
        Optional.empty()

    fun findBusinessCardByName(name: String): MutableList<BusinessCardEntity> {
        return mutableListOf()
    }

    fun findBusinessCardByPhoneNumber(phoneNumber: String): BusinessCardEntity? {
        return null
    }

    fun saveBusinessCard(businessCardEntity: BusinessCardEntity): BusinessCardEntity {
        jdbcTemplate.execute("INSERT INTO CARD VALUES (?, ?, ?)");
        return BusinessCardEntity(0, "test", "test")
    }

    fun deleteBusinessCardById(id: Long) {

    }

}