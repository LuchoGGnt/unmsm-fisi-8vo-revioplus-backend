package com.revioplus.backend.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig (
    private val jwtAuthFilter: JwtAuthenticationFilter
){
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain{
        http
            .csrf {it.disable()}
            .authorizeHttpRequests{ auth ->
                auth
                    .requestMatchers("/api/v1/auth/**").permitAll() //permitir login
                    .anyRequest().authenticated() // lo demas con token JWT
            }
            //Agregar el filtro JWT
            .addFilterBefore(jwtAuthFilter,
                UsernamePasswordAuthenticationFilter::class.java
            )
        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

}