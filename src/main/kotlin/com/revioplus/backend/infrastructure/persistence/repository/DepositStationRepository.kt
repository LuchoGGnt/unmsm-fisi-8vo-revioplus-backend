package com.revioplus.backend.infrastructure.persistence.repository

import com.revioplus.backend.infrastructure.persistence.entity.DepositStationEntity
import org.springframework.data.repository.CrudRepository
import java.util.Optional

interface DepositStationRepository: CrudRepository<DepositStationEntity, Long> {
    override fun findById(id: Long): Optional<DepositStationEntity?>
}