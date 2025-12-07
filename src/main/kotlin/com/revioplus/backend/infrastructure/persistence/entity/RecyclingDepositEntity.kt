package com.revioplus.backend.infrastructure.persistence.entity

import com.revioplus.backend.infrastructure.persistence.dto.RecyclingDepositResponse
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneId

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


fun RecyclingDepositEntity.toResponse(stationName: String) =
    RecyclingDepositResponse(
        id = this.id,
        userId = this.userId,
        stationId = this.stationId,
        stationName = stationName,
        bottlesCount = this.bottles,
        xpGenerated = this.xp,
        co2SavedKg = this.co2SavedKg,
        createdAt = this.createdAt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
    )
