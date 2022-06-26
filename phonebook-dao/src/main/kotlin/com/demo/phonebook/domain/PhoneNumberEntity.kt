package com.demo.phonebook.domain

import javax.persistence.*

@Entity(name = "phone_number")
class PhoneNumberEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "number_id")
    val id : Long,
    val type : String,
    val number : String,
    @ManyToOne
    @JoinColumn(name = "card_id")
    val businessCard : BusinessCardEntity
)