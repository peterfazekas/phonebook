package com.demo.phonebook.config

import com.demo.phonebook.service.BusinessCardRowMapper
import com.demo.phonebook.service.DaoService
import com.demo.phonebook.service.PhoneNumberRowMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.DriverManagerDataSource


@Configuration
class DaoConfig {

    @Bean
    fun dataSource() =
        DriverManagerDataSource().apply {
            setDriverClassName("org.h2.Driver")
            url = "jdbc:h2:mem:phonebook"
            username = "sa"
        }

//    @Bean
//    fun dataSource(): DataSource? {
//        return EmbeddedDatabaseBuilder()
//            .setType(EmbeddedDatabaseType.H2)
//            .addScript("classpath:jdbc/schema.sql")
//            .addScript("classpath:jdbc/test-data.sql").build()
//    }

    @Bean
    fun businessCardRowMapper() = BusinessCardRowMapper()

    @Bean
    fun phoneNumberRowMapper() = PhoneNumberRowMapper()

    @Bean
    fun jdbcTemplate() = JdbcTemplate(dataSource())

    @Bean
    fun daoService() = DaoService(jdbcTemplate(), businessCardRowMapper(), phoneNumberRowMapper())
}