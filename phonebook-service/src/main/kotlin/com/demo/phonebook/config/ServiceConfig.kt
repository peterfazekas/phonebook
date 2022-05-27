package com.demo.phonebook.config

import com.demo.phonebook.service.BusinessCardEntityConverter
import com.demo.phonebook.service.DaoService
import com.demo.phonebook.service.InitDataFactory
import com.demo.phonebook.service.PhoneBookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(DaoConfig::class)
class ServiceConfig {

    @Autowired
    lateinit var daoService: DaoService

    @Bean
    fun businessCardEntityConverter() = BusinessCardEntityConverter()

    @Bean
    fun initDataFactory() = InitDataFactory()

    @Bean
    fun phoneBookService() = PhoneBookService(businessCardEntityConverter(), initDataFactory(), daoService)
}