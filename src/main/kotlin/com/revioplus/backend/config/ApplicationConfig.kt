package com.revioplus.backend.config

import com.revioplus.backend.infrastructure.persistence.repository.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

@Configuration
class ApplicationConfig(
        private val userRepo: UserRepository
) {
    @Bean
    fun userDetailsService(): UserDetailsService {
        return UserDetailsService{
            username ->
            val user = userRepo.findByEmail(username)
                ?: throw UsernameNotFoundException("Usuario no encontrado")

            User.builder()
                .username(user.email)
                .password(user.password)
                .roles("USER")
                .build()
        }
    }
}