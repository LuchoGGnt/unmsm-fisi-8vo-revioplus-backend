package com.revioplus.backend.infrastructure.persistence.repository

import com.revioplus.backend.infrastructure.persistence.entity.WalletEntity
import org.springframework.data.repository.CrudRepository

interface WalletRepository : CrudRepository<WalletEntity, Long> {

    fun findByUserId(userId: Long): WalletEntity?

}