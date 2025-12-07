package com.revioplus.backend.infrastructure.persistence.entity

import org.springframework.context.annotation.Description
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalTime


@Table(name = "deposit_stations")
data class DepositStationEntity (
    @Id val id : Long,
    val name: String,
    val address: String,
    val description : String,
    val district : String,
    val city : String,
    val department : String,
    val country : String,
    @Column("openingHour") val openingHour: LocalTime,
    @Column("closingHour") val closingHour: LocalTime
)