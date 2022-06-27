package com.demo.phonebook.service

import com.demo.phonebook.domain.BusinessCardEntity
import com.demo.phonebook.domain.PhoneNumberEntity
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class BusinessCardRowMapper : RowMapper<BusinessCardEntity> {
    override fun mapRow(rs: ResultSet, rowNum: Int) = BusinessCardEntity(
        rs.getLong("card_id"),
        rs.getString("firstname"),
        rs.getString("lastname")
    )
}

class PhoneNumberRowMapper : RowMapper<PhoneNumberEntity> {
    override fun mapRow(rs: ResultSet, rowNum: Int) = PhoneNumberEntity(
            rs.getLong("number_id"),
            rs.getString("type"),
            rs.getString("number"),
            rs.getLong("card_id")
    )
}