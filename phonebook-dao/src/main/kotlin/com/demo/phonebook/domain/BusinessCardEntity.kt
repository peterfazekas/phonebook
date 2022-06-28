package com.demo.phonebook.domain

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity(name = "business_card")
data class BusinessCardEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "card_id")
    val id: Long,
    val firstname: String,
    val lastname: String
) {
    @OneToMany(mappedBy = "businessCard", cascade = [CascadeType.REMOVE], orphanRemoval = true)
    lateinit var phoneNumbers: List<PhoneNumberEntity>
}