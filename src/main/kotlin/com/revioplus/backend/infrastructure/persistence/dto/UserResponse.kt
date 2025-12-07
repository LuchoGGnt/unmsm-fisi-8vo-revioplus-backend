package com.revioplus.backend.infrastructure.persistence.dto

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * DTO de respuesta para el frontend móvil
 * Los nombres de propiedades coinciden con lo que espera el cliente Android
 */
data class UserResponse(
    @JsonProperty("id") val id: Long,
    @JsonProperty("username") val username: String,
    @JsonProperty("firstName") val firstName: String?,
    @JsonProperty("lastName") val lastName: String?,
    @JsonProperty("email") val email: String,
    @JsonProperty("phone") val phone: String?,
    @JsonProperty("city") val city: String?,
    @JsonProperty("department") val department: String?,
    @JsonProperty("country") val country: String?,
    @JsonProperty("avatarUrl") val avatarUrl: String?,
    
    @JsonProperty("registeredAt") val registeredAt: Long, // timestamp en millis (CALCULADO)
    @JsonProperty("memberSince") val memberSince: String, // "Nov 2024" (CALCULADO)
    
    // Stats / Gamification
    @JsonProperty("totalXp") val totalXp: Long,
    @JsonProperty("currentLevel") val currentLevel: Int,
    @JsonProperty("levelTitle") val levelTitle: String, // (CALCULADO según nivel)
    @JsonProperty("currentStreak") val currentStreak: Int,
    @JsonProperty("maxStreak") val maxStreak: Int,
    
    // Recycling Stats
    @JsonProperty("totalBottles") val totalBottles: Long,
    @JsonProperty("totalCo2Saved") val totalCo2Saved: Double,
    
    // Settings
    @JsonProperty("nfcEnabled") val nfcEnabled: Boolean,

    // Campos faltantes para mapear con la Entidad en Frontend
    @JsonProperty("nextLevelXp") val nextLevelXp: Long,
    @JsonProperty("lastDepositDateMillis") val lastDepositDateMillis: Long?,
    @JsonProperty("termsAccepted") val termsAccepted: Boolean,
    @JsonProperty("acceptedTermsVersion") val acceptedTermsVersion: String?,
    @JsonProperty("termsAcceptedDateMillis") val termsAcceptedDateMillis: Long?,
    @JsonProperty("userType") val userType: String,
    @JsonProperty("accountStatus") val accountStatus : String
)
