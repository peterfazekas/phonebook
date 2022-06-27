package com.demo.phonebook.config

import com.demo.phonebook.service.BusinessCardRowMapper
import com.demo.phonebook.service.DaoService
import com.demo.phonebook.service.PhoneNumberRowMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import javax.sql.DataSource


@Configuration
class DaoConfig {

    @Bean
    fun dataSource(): DataSource {
        return EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .setName("phonebook")
            .addScript("classpath:schema.sql")
            .build()
    }

    @Bean
    fun businessCardRowMapper() = BusinessCardRowMapper()

    @Bean
    fun phoneNumberRowMapper() = PhoneNumberRowMapper()

    @Bean
    fun jdbcTemplate() = JdbcTemplate(dataSource())

    @Bean
    fun namedJdbcTemplate () = NamedParameterJdbcTemplate(dataSource())

    @Bean
    fun daoService() = DaoService(jdbcTemplate(), namedJdbcTemplate(), businessCardRowMapper(), phoneNumberRowMapper())
}


