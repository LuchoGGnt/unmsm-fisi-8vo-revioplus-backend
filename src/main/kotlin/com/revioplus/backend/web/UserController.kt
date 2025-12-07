package com.revioplus.backend.web

import com.revioplus.backend.domain.service.RecyclingDepositService
import com.revioplus.backend.domain.service.UserService
import com.revioplus.backend.domain.service.WalletService
import com.revioplus.backend.infrastructure.persistence.dto.RecyclingDepositResponse
import com.revioplus.backend.infrastructure.persistence.dto.UserResponse
import com.revioplus.backend.infrastructure.persistence.dto.WalletResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val userService : UserService,
    private val walletService : WalletService,
    private val recyclingDepositService: RecyclingDepositService
) {

    @GetMapping("/{userId}")
    fun getUserData(@PathVariable userId: Long): ResponseEntity<UserResponse> = ResponseEntity
        .ok().body(userService.getUserById(userId))

    @GetMapping
    fun getAllUser(): ResponseEntity<List<UserResponse>> = ResponseEntity
        .ok().body(userService.getAllUsers())

    @GetMapping("/{userId}/wallet")
    fun getWalletData(@PathVariable userId: Long): ResponseEntity<WalletResponse> =
        ResponseEntity.ok().body(walletService.getWalletByUserId(userId))

    @GetMapping("/{userId}/deposits")
    fun getDepositsData(@PathVariable userId: Long): ResponseEntity<List<RecyclingDepositResponse>> =
        ResponseEntity.ok().body(recyclingDepositService.getDepositsByUserId(userId))
}