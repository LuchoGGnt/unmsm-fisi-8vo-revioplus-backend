package com.revioplus.backend.infrastructure.persistence.dto

import com.revioplus.backend.infrastructure.persistence.entity.UserChallengeEntity

data class ChallengeProgressRequestDto(
    val userId: Long,
    val bottles: Int
)

fun ChallengeProgressRequestDto.toEntity() =
    UserChallengeEntity(
        userId = this.userId,
        currentProgressBottles = this.bottles
    )