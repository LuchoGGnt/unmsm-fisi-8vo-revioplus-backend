package com.revioplus.backend.infrastructure.persistence.dto
import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class WalletResponse(
    @JsonProperty("id") val id: Long,
    @JsonProperty("userId") val userId: Long,
    @JsonProperty("availableBalance") val availableBalance: BigDecimal?,
    @JsonProperty("lockedBalance") val lockedBalance: BigDecimal?,
    @JsonProperty("currency") val currency: String?,
    @JsonProperty("status") val status: String?,
    @JsonProperty("createdAt") val createdAt: Long?,
    @JsonProperty("lastMovementAt") val lastMovementAt: Long?,
    @JsonProperty("dailyWithdrawalLimit") val dailyWithdrawalLimit: BigDecimal?,
    @JsonProperty("minWithdrawalAmount") val minWithdrawalAmount: BigDecimal?
)
