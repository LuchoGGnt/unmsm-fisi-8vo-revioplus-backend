package com.revioplus.backend.infrastructure.persistence.repository

import com.revioplus.backend.infrastructure.persistence.entity.RecyclingDepositEntity
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository

interface RecyclingDepositJdbcRepository : CrudRepository<RecyclingDepositEntity, Long> {

    fun findTop5ByUserId(userId: Long): List<RecyclingDepositEntity>

    @Query(
        """
            SELECT COALESCE(SUM(bottles), 0) AS totalBottles,
                   COALESCE(SUM(co2_saved_kg), 0) AS totalCo2Kg
            FROM recycling_deposits
            WHERE user_id = :userId
        """
    )
    fun getStatsForUser(userId: Long): RecyclingStatsRow
}

data class RecyclingStatsRow(
    val totalBottles: Long?,
    val totalCo2Kg: Double?
)
