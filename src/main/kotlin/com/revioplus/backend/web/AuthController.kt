package com.revioplus.backend.web

import com.revioplus.backend.domain.service.AuthService
import com.revioplus.backend.infrastructure.persistence.dto.LoginRequestDto
import com.revioplus.backend.infrastructure.persistence.dto.LoginResponseDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/auth")
class AuthController (
    private val authService : AuthService
){
    @PostMapping("/login")
    fun login(@RequestBody loginRequestDto: LoginRequestDto): ResponseEntity<LoginResponseDto> {
        return try {
            val response = authService.loginUser(loginRequestDto)
            ResponseEntity.ok(response)
        } catch (e: IllegalArgumentException) {
            // Devuelve 401 Unauthorized si falla el login
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        }
    }
}
