package com.demo.phonebook.config

import com.demo.phonebook.repository.BusinessCardRepository
import com.demo.phonebook.repository.PhoneNumberRepository
import com.demo.phonebook.service.DaoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DaoConfig {

    @Autowired
    lateinit var businessCardRepository: BusinessCardRepository
    @Autowired
    lateinit var phoneNumberRepository: PhoneNumberRepository

    @Bean
    fun daoService() = DaoService(businessCardRepository, phoneNumberRepository)
}