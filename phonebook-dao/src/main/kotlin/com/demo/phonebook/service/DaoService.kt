package com.demo.phonebook.service

import com.demo.phonebook.domain.BusinessCardEntity
import com.demo.phonebook.domain.PhoneNumberEntity
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate


class DaoService(
    private val jdbcTemplate: JdbcTemplate,
    private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate,
    private val businessCardRowMapper: BusinessCardRowMapper,
    private val phoneNumberRowMapper: PhoneNumberRowMapper) {

    fun findAllBusinessCard(): List<BusinessCardEntity> =
        jdbcTemplate.query("SELECT * FROM BUSINESS_CARD", businessCardRowMapper)

    fun findBusinessCardById(id: Long): MutableList<BusinessCardEntity> {
        val namedParameters = MapSqlParameterSource().addValue("id", id)
        val sqlQuery = "SELECT * FROM BUSINESS_CARD WHERE card_id = :id"
        return namedParameterJdbcTemplate.query(sqlQuery, namedParameters, businessCardRowMapper)
    }

    fun findAllPhoneNumber(): List<PhoneNumberEntity> =
        jdbcTemplate.query("SELECT * FROM PHONE_NUMBER", phoneNumberRowMapper)

    fun findPhoneNumberByCardId(cardId: Long): MutableList<PhoneNumberEntity> {
        val namedParameters = MapSqlParameterSource().addValue("id", cardId)
        val sqlQuery = "SELECT * FROM PHONE_NUMBER WHERE CARD_ID = :id"
        return namedParameterJdbcTemplate.query(sqlQuery, namedParameters, phoneNumberRowMapper)
    }

    fun findBusinessCardByName(name: String): MutableList<BusinessCardEntity> {
        val namedParameters = MapSqlParameterSource().addValue("name", name)
        val sqlQuery = "SELECT * FROM BUSINESS_CARD WHERE firstname LIKE :name OR lastname LIKE :name"
        return namedParameterJdbcTemplate.query(sqlQuery, namedParameters, businessCardRowMapper)
    }

    fun findBusinessCardByPhoneNumber(phoneNumber: String): MutableList<PhoneNumberEntity> {
        val namedParameters = MapSqlParameterSource().addValue("number", phoneNumber)
        val sqlQuery = "SELECT * FROM PHONE_NUMBER WHERE number LIKE :number"
        return namedParameterJdbcTemplate.query(sqlQuery, namedParameters, phoneNumberRowMapper)
    }

    fun findBusinessCardByType(type: String): MutableList<PhoneNumberEntity> {
        val namedParameters = MapSqlParameterSource().addValue("type", type)
        val sqlQuery = "SELECT * FROM PHONE_NUMBER WHERE type LIKE :type"
        return namedParameterJdbcTemplate.query(sqlQuery, namedParameters, phoneNumberRowMapper)
    }

    fun saveBusinessCard(businessCardEntity: BusinessCardEntity, phoneNumberEntities: List<PhoneNumberEntity>): BusinessCardEntity {
        val cardNamedParameters = MapSqlParameterSource()
            .addValue("firstname", businessCardEntity.firstname)
            .addValue("lastname", businessCardEntity.lastname)
        val cardSqlQuery = "INSERT INTO BUSINESS_CARD (FIRSTNAME, LASTNAME) VALUES(:firstname, :lastname);"
        namedParameterJdbcTemplate.update(cardSqlQuery, cardNamedParameters)
        val savedBusinessCardEntity = findBusinessCardByFullName(businessCardEntity.firstname, businessCardEntity.lastname)
        val cardId = if (savedBusinessCardEntity.size > 0) savedBusinessCardEntity.last().id else 0
        phoneNumberEntities.forEach {
            val phoneNumberNamedParameters = MapSqlParameterSource()
                .addValue("type", it.type)
                .addValue("number", it.number)
                .addValue("cardId", cardId)
            val phoneNumberSqlQuery = "INSERT INTO PHONE_NUMBER (TYPE, NUMBER, CARD_ID) VALUES(:type, :number, :cardId);"
            namedParameterJdbcTemplate.update(phoneNumberSqlQuery, phoneNumberNamedParameters)
        }
        return savedBusinessCardEntity.last()
    }

    private fun findBusinessCardByFullName(firstname: String, lastname:String): MutableList<BusinessCardEntity> {
        val namedParameters = MapSqlParameterSource()
            .addValue("firstname", firstname)
            .addValue("lastname", lastname)
        val sqlQuery = "SELECT * FROM BUSINESS_CARD WHERE firstname LIKE :firstname AND lastname LIKE :lastname"
        return namedParameterJdbcTemplate.query(sqlQuery, namedParameters, businessCardRowMapper)
    }

    fun deleteBusinessCardById(id: Long) {
        val phoneNumberNamedParameters = MapSqlParameterSource()
            .addValue("card_id", id)
        val phoneNumberSqlQuery = "DELETE FROM PHONE_NUMBER WHERE card_id = :card_id;"
        namedParameterJdbcTemplate.update(phoneNumberSqlQuery, phoneNumberNamedParameters)
        val cardNamedParameters = MapSqlParameterSource()
            .addValue("card_id", id)
        val cardSqlQuery = "DELETE FROM BUSINESS_CARD WHERE card_id = :card_id;"
        namedParameterJdbcTemplate.update(cardSqlQuery, cardNamedParameters)
    }

}