package com.revioplus.backend.web

import com.revioplus.backend.domain.service.RecyclingDepositService
import com.revioplus.backend.infrastructure.persistence.dto.RecyclingDepositRequest
import com.revioplus.backend.infrastructure.persistence.dto.RecyclingDepositResponse
import com.revioplus.backend.infrastructure.persistence.dto.toEntity
import com.revioplus.backend.infrastructure.persistence.entity.toResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/deposits")
class RecyclingDepositController (
    private val recyclingDepositService: RecyclingDepositService,
){
    @PostMapping
    fun registerRecyclingDeposit(@RequestBody recyclingDepositRequest : RecyclingDepositRequest) : ResponseEntity<RecyclingDepositResponse>{
        //1. Mapeamos a la entidad
        val recyclingDeposit = recyclingDepositRequest.toEntity()
        // 2. Obtenemos el nombre de la estacion (para el response)
        val stationName = recyclingDepositService.registerDeposit(recyclingDeposit)
        // 3. Devolvemos la respuesta al frontend
        return ResponseEntity.ok(recyclingDeposit.toResponse(stationName!!))
    }

}