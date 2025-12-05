package com.revioplus.backend.domain.service

import com.revioplus.backend.infrastructure.persistence.dto.RecyclingDepositResponse
import com.revioplus.backend.infrastructure.persistence.entity.RecyclingDepositEntity
import com.revioplus.backend.infrastructure.persistence.repository.DepositStationRepository
import com.revioplus.backend.infrastructure.persistence.repository.RecyclingDepositRepository
import com.revioplus.backend.infrastructure.persistence.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.ZoneId

@Service
class RecyclingDepositService(
    private val depositStationRepo: DepositStationRepository,
    private val recyclingDepositRepo: RecyclingDepositRepository,
    private val userRepo: UserRepository
) {
    fun getDepositsByUserId(userId:Long) : List<RecyclingDepositResponse>? {
        val deposits = recyclingDepositRepo.findByUserId(userId)
            ?: throw NoSuchElementException("No se pudieron encontrar los depositos del usuario")
        return mapToDTO(deposits)
    }

    private fun mapToDTO(deposits : List<RecyclingDepositEntity>) = deposits
        .map {it ->
            RecyclingDepositResponse(
                id = it.id,
                userId = it.userId,
                stationId = it.stationId,
                stationName = depositStationRepo.findById(it.stationId).get().name,
                bottlesCount = it.bottles,
                xpGenerated = it.xp,
                co2SavedKg = it.co2SavedKg,
                createdAt = it.createdAt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
            )
        }
    @Transactional
    fun registerDeposit(recyclingDepositEntity: RecyclingDepositEntity) : String? {
        // 1. Guardar el historial del depósito
        recyclingDepositRepo.save(recyclingDepositEntity)

        // 2. Actualizar las estadísticas del usuario
        val user = userRepo.findById(recyclingDepositEntity.userId)
            .orElseThrow { NoSuchElementException("Usuario no encontrado") }

        val updatedUser = user.copy(
            totalBottles = user.totalBottles + recyclingDepositEntity.bottles,
            totalCo2Saved = user.totalCo2Saved + recyclingDepositEntity.co2SavedKg,
            totalXp = user.totalXp + recyclingDepositEntity.xp,
            lastDepositDate = recyclingDepositEntity.createdAt
        )

        userRepo.save(updatedUser)

        return depositStationRepo.findById(recyclingDepositEntity.stationId).get().name
    }
}
