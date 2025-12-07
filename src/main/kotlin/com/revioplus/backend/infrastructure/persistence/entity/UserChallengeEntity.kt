package com.revioplus.backend.infrastructure.persistence.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("user_challenges")
data class UserChallengeEntity(
    @Id val id: Long? = null,
    @Column("challengeId") val challengeId: Long? = null,
    @Column("userId") val userId: Long,
    @Column("currentProgressBottles") val currentProgressBottles: Int,
    @Column("completed") val completed: Boolean? = null,
)
