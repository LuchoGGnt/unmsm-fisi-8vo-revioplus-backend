package com.revioplus.backend.infrastructure.persistence.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ChallengeDto(
    @JsonProperty("id") val id: Long,
    @JsonProperty("title") val title: String,
    @JsonProperty("shortDescription") val shortDescription: String,
    @JsonProperty("longDescription") val longDescription: String?,
    @JsonProperty("startDate") val startDate: Long,
    @JsonProperty("endDate") val endDate: Long,
    @JsonProperty("targetBottles") val targetBottles: Int,
    @JsonProperty("rewardXp") val rewardXp: Long,
    
    @JsonProperty("currentProgressBottles") val currentProgressBottles: Int
)
