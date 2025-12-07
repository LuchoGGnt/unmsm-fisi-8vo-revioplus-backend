package com.revioplus.backend.infrastructure.persistence.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class RecyclingDepositResponse(
    @JsonProperty val id: Long?,
    @JsonProperty val userId: Long,
    @JsonProperty val stationId: Long,
    @JsonProperty val stationName: String,
    @JsonProperty val bottlesCount: Int,
    @JsonProperty val xpGenerated: Long,
    @JsonProperty val co2SavedKg: Double,
    @JsonProperty val createdAt: Long
)
