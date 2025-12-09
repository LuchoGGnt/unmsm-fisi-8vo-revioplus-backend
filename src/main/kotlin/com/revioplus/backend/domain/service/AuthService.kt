package com.revioplus.backend.domain.service

import com.revioplus.backend.config.JwtService
import com.revioplus.backend.infrastructure.persistence.dto.LoginRequestDto
import com.revioplus.backend.infrastructure.persistence.dto.LoginResponseDto
import com.revioplus.backend.infrastructure.persistence.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService (
    private val userRepo: UserRepository,
    //Encriptacion de contraseñas
    private val passwordEncoder: PasswordEncoder,
    private val userDetailsService: UserDetailsService,
    private val jwtService: JwtService
    ){
    fun loginUser(loginRequestDto : LoginRequestDto): LoginResponseDto{
        //1. buscamos el email del usuario en la base de datus
        val user = userRepo.findByEmail(loginRequestDto.email)
            ?: throw IllegalArgumentException("Usuario no encontrado")

        //2. Comparamos contraseñas (matches devuelve true si coinciden)
        // Por lo tanto, si NO coinciden (!), lanzamos el error.
        if(!passwordEncoder.matches(loginRequestDto.password, user.password))
            throw IllegalArgumentException("Contraseña incorrecta")

        val userDetails = userDetailsService.loadUserByUsername(user.email)
        val jwtToken = jwtService.generateToken(userDetails)

        //3. Devolvemos la llamada a la API
        return LoginResponseDto(
            id = user.id!!,
            email = user.email,
            username = user.username,
            token = jwtToken
        )
    }
}