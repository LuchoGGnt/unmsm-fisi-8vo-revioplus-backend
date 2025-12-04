package com.revioplus.backend.domain.service

import com.revioplus.backend.infrastructure.persistence.dto.RecyclingDepositResponse
import com.revioplus.backend.infrastructure.persistence.entity.RecyclingDepositEntity
import com.revioplus.backend.infrastructure.persistence.repository.DepositStationRepository
import com.revioplus.backend.infrastructure.persistence.repository.RecyclingDepositJdbcRepository
import com.revioplus.backend.infrastructure.persistence.repository.RecyclingDepositRepository
import com.revioplus.backend.infrastructure.persistence.repository.UserRepository
import org.springframework.stereotype.Service
import java.time.ZoneId

@Service
class RecyclingService(
    private val depositStationRepo: DepositStationRepository,
    private val recyclingDepositRepo: RecyclingDepositRepository
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

 /*
    fun registerDeposit(userId: Long, bottles: Int): RecyclingDeposit {
        require(bottles > 0) { "La cantidad de botellas debe ser mayor a cero" }

        val xp = bottles * 10L
        val co2 = bottles * 0.5

        val saved = depositRepo.save(
            RecyclingDepositEntity(
                userId = userId,
                dateTime = OffsetDateTime.now(),
                bottles = bottles,
                xp = xp,
                co2SavedKg = co2
            )
        )

        return RecyclingDeposit(
            id = saved.id!!,
            userId = saved.userId,
            bottles = saved.bottles,
            xp = saved.xp,
            co2SavedKg = saved.co2SavedKg
        )
    }

    fun getHomeDashboard(userId: Long): HomeDashboard {
        val userEntity = userRepo.findById(userId)
            .orElseThrow { IllegalArgumentException("Usuario $userId no existe") }

        val statsRow = depositRepo.getStatsForUser(userId)
        val stats = RecyclingStats(
            totalBottles = statsRow.totalBottles ?: 0L,
            totalCo2Kg = statsRow.totalCo2Kg ?: 0.0
        )

        val recent = depositRepo
            .findTop5ByUserIdOrderByDateTimeDesc(userId)
            .map {
                RecyclingDeposit(
                    id = it.id,
                    userId = it.userId,
                    bottles = it.bottles,
                    xp = it.xp,
                    co2SavedKg = it.co2SavedKg
                )
            }

        val user = User(
            id = userEntity.id!!,
            displayName = userEntity.displayName,
            city = userEntity.city,
            country = userEntity.country,
            currentLevel = userEntity.currentLevel,
            currentXp = userEntity.currentXp,
            streakDays = userEntity.streakDays
        )

        return HomeDashboard(
            user = user,
            stats = stats,
            recentDeposits = recent
        )
    }
  */
}
