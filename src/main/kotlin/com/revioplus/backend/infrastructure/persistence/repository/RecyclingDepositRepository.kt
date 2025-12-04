package com.revioplus.backend.infrastructure.persistence.repository

import com.revioplus.backend.domain.model.RecyclingDeposit
import com.revioplus.backend.infrastructure.persistence.entity.RecyclingDepositEntity
import org.springframework.data.repository.CrudRepository

interface RecyclingDepositRepository : CrudRepository<RecyclingDepositEntity, Long> {
    fun findByUserId(userId : Long) : List<RecyclingDepositEntity>?
}