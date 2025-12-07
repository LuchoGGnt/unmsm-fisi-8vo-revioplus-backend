package com.revioplus.backend.web

import com.revioplus.backend.domain.service.ChallengeService
import com.revioplus.backend.infrastructure.persistence.dto.ChallengeDto
import com.revioplus.backend.infrastructure.persistence.dto.ChallengeProgressRequestDto
import com.revioplus.backend.infrastructure.persistence.dto.toEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/challenges")
class ChallengeController(
    private val challengeService: ChallengeService
) {

    @GetMapping("/active")
    fun getActiveChallenge(@RequestParam userId: Long): ResponseEntity<ChallengeDto> {
        val challenge = challengeService.getActiveChallengeForUser(userId)
        return if (challenge != null) {
            ResponseEntity.ok(challenge)
        } else {
            ResponseEntity.noContent().build()
        }
    }

    @PutMapping("/progress")
    fun updateChallengeProgress(@RequestBody request: ChallengeProgressRequestDto){
        val challenge = request.toEntity()
        // Aquí deberías llamar a tu servicio para guardar la entidad, ej
        challengeService.updateProgress(challenge)
    }
}
