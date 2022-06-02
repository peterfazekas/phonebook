package com.demo.phonebook.domain

import javax.persistence.*

@Entity(name = "business_card")
data class BusinessCardEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id : Long,
    val firstname : String,
    val lastname : String,
    @Column(name="phone_number")
    val phoneNumber : String)