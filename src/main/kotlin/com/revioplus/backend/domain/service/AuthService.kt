package com.revioplus.backend.domain.service

import com.revioplus.backend.infrastructure.persistence.dto.LoginRequestDto
import com.revioplus.backend.infrastructure.persistence.dto.LoginResponseDto
import com.revioplus.backend.infrastructure.persistence.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class AuthService (private val userRepo: UserRepository){

    fun loginUser(loginRequestDto : LoginRequestDto): LoginResponseDto{
        //1. buscamos el email del usuario en la base de datus
        val user = userRepo.findByEmail(loginRequestDto.email)
            ?: throw IllegalArgumentException("Usuario no encontrado")

        //2. Comparamos contraseñas

        if(loginRequestDto.password != user.password)
            throw IllegalArgumentException("Contraseña incorrecta")

        //3. Devolvemos la llamada a la API
        return LoginResponseDto(
            id = user.id!!,
            email = user.email,
            username = user.username
        )
    }

}