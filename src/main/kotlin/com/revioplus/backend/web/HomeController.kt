package com.revioplus.backend.web

import com.revioplus.backend.domain.model.HomeDashboard
import com.revioplus.backend.domain.model.RecyclingDeposit
import com.revioplus.backend.domain.service.RecyclingService
import jakarta.validation.constraints.Min
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
@Validated
class HomeController(
    private val recyclingService: RecyclingService
) {

    data class CreateDepositRequest(
        val userId: Long,
        @field:Min(1) val bottles: Int
    )

    @GetMapping("/home")
    fun getHome(@RequestParam userId: Long): HomeDashboard =
        recyclingService.getHomeDashboard(userId)

    @PostMapping("/deposits")
    fun createDeposit(@RequestBody req: CreateDepositRequest): RecyclingDeposit =
        recyclingService.registerDeposit(req.userId, req.bottles)
}
