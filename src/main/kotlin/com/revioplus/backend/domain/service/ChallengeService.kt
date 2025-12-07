package com.revioplus.backend.domain.service

import com.revioplus.backend.infrastructure.persistence.dto.ChallengeDto
import com.revioplus.backend.infrastructure.persistence.entity.UserChallengeEntity
import com.revioplus.backend.infrastructure.persistence.repository.ChallengeRepository
import com.revioplus.backend.infrastructure.persistence.repository.UserChallengeProgressRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
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

    @Transactional
    fun updateProgress(progressUpdate: UserChallengeEntity) {
            // 1. Traer el desafio actual
        val actualChallenge = challengeRepository.findActiveChallenge(LocalDateTime.now())
            ?: throw NoSuchElementException("No se encontró ningun Challenge activo")
        // 2. Traer el progreso actual del challenge del usuario
        val existingChallengeProgress = progressRepository
            .findByUserIdAndChallengeId(progressUpdate.userId, actualChallenge.id!!)
        // 3. Actualizar progreso del usuario
        val newBottlesAmmount = progressUpdate.currentProgressBottles +
                (existingChallengeProgress?.currentProgressBottles ?: 0)
        val newChallengeProgress = progressUpdate.copy(
            id = existingChallengeProgress?.id,
            challengeId = actualChallenge.id,
            userId = progressUpdate.userId,
            currentProgressBottles = newBottlesAmmount,
            completed = newBottlesAmmount >= actualChallenge.targetBottles,
        )
        // 4. Guardar el progreso
        progressRepository.save(newChallengeProgress)
    }
}
