package com.revioplus.backend.infrastructure.persistence.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("users")
data class UserEntity(
    @Id val id: Long,
    val username: String, // nombreMostrar
    @Column("firstName")
    val firstName: String?,
    @Column("lastName")
    val lastName: String?,
    val email: String,
    val phone: String?,
    val city: String?,
    val department: String?,
    val country: String?,
    @Column("avatarUrl")
    val avatarUrl: String?,
    @Column("registeredAt")
    val registeredAt: LocalDateTime, // timestamp millis
    @Column("memberSince")
    val memberSince: String, // Texto formateado ej: "Oct 2025"

    // Stats / Gamification
    @Column("totalXp")
    val totalXp: Long,
    @Column("currentLevel")
    val currentLevel: Int,
    @Column("levelTitle")
    val levelTitle: String,
    @Column("currentStreak")
    val currentStreak: Int,
    @Column("maxStreak")
    val maxStreak: Int,

    // Recycling Stats
    @Column("totalBottles")
    val totalBottles: Long,
    @Column("totalCo2Saved")
    val totalCo2Saved: Double,

    // Settings
    @Column("nfcEnabled")
    val nfcEnabled: Boolean
)
