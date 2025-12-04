package com.revioplus.backend.domain.service

import com.revioplus.backend.infrastructure.persistence.dto.ChallengeDto
import com.revioplus.backend.infrastructure.persistence.repository.ChallengeRepository
import com.revioplus.backend.infrastructure.persistence.repository.UserChallengeProgressRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneId

@Service
class ChallengeService(
    private val challengeRepository: ChallengeRepository,
    private val progressRepository: UserChallengeProgressRepository
) {
    fun getActiveChallengeForUser(userId: Long): ChallengeDto? {
        val now = LocalDateTime.now()
        val challenge = challengeRepository.findActiveChallenge(now) ?: return null
        
        val progress = progressRepository.findByUserIdAndChallengeId(userId, challenge.id!!)
        
        val currentBottles = progress?.currentProgressBottles ?: 0
        
        return ChallengeDto(
            id = challenge.id,
            title = challenge.title,
            shortDescription = challenge.shortDescription,
            longDescription = challenge.longDescription,
            startDate = challenge.startDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
            endDate = challenge.endDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
            targetBottles = challenge.targetBottles,
            rewardXp = challenge.rewardXp,
            currentProgressBottles = currentBottles
        )
    }
}
