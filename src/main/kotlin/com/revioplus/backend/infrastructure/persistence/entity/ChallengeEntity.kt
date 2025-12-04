package com.revioplus.backend.infrastructure.persistence.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("challenges")
data class ChallengeEntity(
    @Id val id: Long? = null,
    val title: String,
    @Column("shortDescription") val shortDescription: String,
    @Column("longDescription") val longDescription: String?,
    @Column("startDate") val startDate: LocalDateTime,
    @Column("endDate") val endDate: LocalDateTime,
    @Column("targetBottles") val targetBottles: Int,
    @Column("rewardXp") val rewardXp: Long
)
