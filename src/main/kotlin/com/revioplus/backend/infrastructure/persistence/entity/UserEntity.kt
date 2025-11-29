package com.revioplus.backend.infrastructure.persistence.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.OffsetDateTime

@Table("users")
data class UserEntity(
    @Id val id: Long? = null,
    val displayName: String,
    val email: String,
    val city: String?,
    val country: String = "Perú",
    val currentLevel: Int = 1,
    val currentXp: Long = 0,
    val streakDays: Int = 0,
    val registeredAt: OffsetDateTime = OffsetDateTime.now()
)
