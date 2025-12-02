package com.revioplus.backend.domain.service
import com.revioplus.backend.infrastructure.persistence.dto.UserResponse
import com.revioplus.backend.infrastructure.persistence.entity.UserEntity
import com.revioplus.backend.infrastructure.persistence.repository.UserJdbcRepository
import org.springframework.stereotype.Service
import java.time.ZoneId

@Service
class UserService(
    private val userRepo: UserJdbcRepository,
) {

    /*Obtener usuario por ID*/
    fun getUserById(userId: Long): UserResponse {
        val user = userRepo.findById(userId)
            .orElseThrow { NoSuchElementException("Usuario $userId no existe") }

        return mapToDTO(user)
    }

    fun getAllUsers():List<UserResponse> {
        return userRepo.findAll().map{mapToDTO(it)}
    }

    fun mapToDTO(user: UserEntity) = UserResponse(
        id = user.id,
        username = user.username,
        firstName = user.firstName,
        lastName = user.lastName,
        email = user.email,
        phone = user.phone,
        city = user.city,
        department = user.department,
        country = user.country,
        avatarUrl = user.avatarUrl,
        registeredAt = user.registeredAt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
        memberSince = user.memberSince,
        totalXp = user.totalXp,
        currentLevel = user.currentLevel,
        levelTitle = user.levelTitle,
        currentStreak = user.currentStreak,
        maxStreak = user.maxStreak,
        totalBottles = user.totalBottles,
        totalCo2Saved = user.totalCo2Saved,
        nfcEnabled = user.nfcEnabled
    )

}