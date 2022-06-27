package com.demo.phonebook.domain

import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import javax.persistence.*

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
    @Fetch(FetchMode.SUBSELECT)
    lateinit var phoneNumbers: List<PhoneNumberEntity>
}