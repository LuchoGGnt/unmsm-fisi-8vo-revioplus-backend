package com.revioplus.backend.infrastructure.persistence.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.revioplus.backend.infrastructure.persistence.entity.RecyclingDepositEntity
import java.time.LocalDateTime

data class RecyclingDepositRequest(
    @JsonProperty val userId: Long,
    @JsonProperty val stationId: Long,
    @JsonProperty val bottlesCount: Int
)

fun RecyclingDepositRequest.toEntity(): RecyclingDepositEntity {
    // Lógica de negocio: 10 XP por botella, 0.1 Kg CO2 por botella
    val xpCalculated = this.bottlesCount * 10L
    val co2Calculated = this.bottlesCount * 0.1 // 100g por botella

    return RecyclingDepositEntity(
        userId = this.userId,
        stationId = this.stationId,
        bottles = this.bottlesCount,
        xp = xpCalculated,
        co2SavedKg = co2Calculated,
        createdAt = LocalDateTime.now()
    )
}
