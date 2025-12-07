package com.revioplus.backend.infrastructure.persistence.repository

import com.revioplus.backend.infrastructure.persistence.entity.UserChallengeEntity
import org.springframework.data.repository.CrudRepository

interface UserChallengeProgressRepository : CrudRepository<UserChallengeEntity, Long> {
    fun findByUserIdAndChallengeId(userId: Long, challengeId: Long): UserChallengeEntity?
}
