package com.revioplus.backend.infrastructure.persistence.repository

import com.revioplus.backend.infrastructure.persistence.entity.ChallengeEntity
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import java.time.LocalDateTime

interface ChallengeRepository : CrudRepository<ChallengeEntity, Long> {

    @Query("SELECT TOP 1 * FROM challenges WHERE startDate <= :now AND endDate >= :now")
    fun findActiveChallenge(now: LocalDateTime): ChallengeEntity?
}
