package com.revioplus.backend.infrastructure.persistence.entity
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.LocalDateTime

@Table("wallets")
data class WalletEntity(
    @Id val id: Long,
    @Column("userId")  val userId: Long,
    @Column("availableBalance")  val availableBalance: BigDecimal?,
    @Column("lockedBalance") val lockedBalance: BigDecimal?,
    @Column("currency") val currency: String?,
    @Column("status") val status: String?,
    @Column("createdAt") val createdAt: LocalDateTime?,
    @Column("lastMovementAt") val lastMovementAt: LocalDateTime?,
    @Column("dailyWithdrawalLimit") val dailyWithdrawalLimit: BigDecimal?,
    @Column("minWithdrawalAmount") val minWithdrawalAmount: BigDecimal?
)