package com.revioplus.backend.infrastructure.persistence.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.OffsetDateTime

@Table("recycling_deposits")
data class RecyclingDepositEntity(
    @Id val id: Long? = null,
    val userId: Long,
    val dateTime: OffsetDateTime = OffsetDateTime.now(),
    val bottles: Int,
    val xp: Long,
    val co2SavedKg: Double
)