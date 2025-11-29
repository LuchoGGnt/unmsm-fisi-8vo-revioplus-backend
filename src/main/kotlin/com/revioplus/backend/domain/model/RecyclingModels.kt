package com.revioplus.backend.domain.model

data class User(
    val id: Long,
    val displayName: String,
    val city: String?,
    val country: String = "Perú",
    val currentLevel: Int,
    val currentXp: Long,
    val streakDays: Int
)

data class RecyclingStats(
    val totalBottles: Long,
    val totalCo2Kg: Double
)

data class RecyclingDeposit(
    val id: Long?,
    val userId: Long,
    val bottles: Int,
    val xp: Long,
    val co2SavedKg: Double
)

data class HomeDashboard(
    val user: User,
    val stats: RecyclingStats,
    val recentDeposits: List<RecyclingDeposit>
)
