package com.demo.phonebook.config

import com.demo.phonebook.service.BusinessCardConverter
import com.demo.phonebook.service.PhoneBookService
import com.demo.phonebook.service.WebService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(ServiceConfig::class)
class WebConfig {

    @Autowired
    lateinit var phoneBookService: PhoneBookService

    @Bean
    fun businessCardConverter() = BusinessCardConverter()

    @Bean
    fun webService() = WebService(businessCardConverter(), phoneBookService)
}