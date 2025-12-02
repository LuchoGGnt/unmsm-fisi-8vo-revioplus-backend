package com.revioplus.backend.web

import com.revioplus.backend.domain.service.UserService
import com.revioplus.backend.infrastructure.persistence.dto.UserResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/users")
class UserController(private val userService : UserService) {

    @GetMapping("/{userId}")
    fun getUserData(@PathVariable userId: Long): ResponseEntity<UserResponse> {
        val user = userService.getUserById(userId)
        return ResponseEntity.ok().body(user)
    }

    @GetMapping
    fun getAllUser(): ResponseEntity<List<UserResponse>> {
        val users = userService.getAllUsers()
        return ResponseEntity.ok().body(users)
    }
}