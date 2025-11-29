package com.revioplus.backend.infrastructure.persistence.repository

import com.revioplus.backend.infrastructure.persistence.entity.UserEntity
import org.springframework.data.repository.CrudRepository

interface UserJdbcRepository : CrudRepository<UserEntity, Long>