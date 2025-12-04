package com.revioplus.backend.infrastructure.persistence.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime
import java.time.OffsetDateTime

@Table("recycling_deposits")
data class RecyclingDepositEntity(
    @Id val id: Long? = null,
    @Column("stationId") val stationId: Long,
    @Column("userId") val userId: Long,
    val bottles: Int,
    val xp: Long,
    @Column("co2SavedKg") val co2SavedKg: Double,
    @Column("createdAt") val createdAt: LocalDateTime,
)