package com.revioplus.backend.infrastructure.persistence.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class LoginRequestDto(
    @JsonProperty val email:String,
    @JsonProperty val password: String,
    )

data class LoginResponseDto(
    @JsonProperty("id") val id: Long,
    @JsonProperty("username") val username: String,
    @JsonProperty("email") val email: String,
    @JsonProperty("token") val token: String,
)