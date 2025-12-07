package com.revioplus.backend.domain.service

import com.revioplus.backend.infrastructure.persistence.dto.WalletResponse
import com.revioplus.backend.infrastructure.persistence.entity.WalletEntity
import com.revioplus.backend.infrastructure.persistence.repository.WalletRepository
import org.springframework.stereotype.Service
import java.time.ZoneId

@Service
class WalletService(
     val walletRepo: WalletRepository
) {
    fun getWalletByUserId(userId: Long) : WalletResponse? {
        /*Pedimos al repo que nos traiga el wallet entity*/
        val walletEntity = walletRepo.findByUserId(userId)
            ?: throw NoSuchElementException("billetera no encontrada :(")

        /*con ello retornamos al controller el DTO*/
        return mapToDTO(walletEntity)
    }

    private fun mapToDTO(walletEntity: WalletEntity): WalletResponse = WalletResponse(
            id = walletEntity.id,
            userId = walletEntity.userId,
            availableBalance = walletEntity.availableBalance,
            lockedBalance = walletEntity.lockedBalance,
            currency = walletEntity.currency,
            status = walletEntity.status,
            createdAt = walletEntity.createdAt?.atZone(ZoneId.systemDefault())?.toInstant()?.toEpochMilli()?:0,
            lastMovementAt = walletEntity.lastMovementAt?.atZone(ZoneId.systemDefault())?.toInstant()?.toEpochMilli()?:0,
            dailyWithdrawalLimit = walletEntity.dailyWithdrawalLimit,
            minWithdrawalAmount = walletEntity.minWithdrawalAmount
        )
}