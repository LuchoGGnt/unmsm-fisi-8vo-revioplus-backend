package com.revioplus.backend.config

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.task.DelegatingSecurityContextTaskExecutor
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter (
    private val jwtService: JwtService,
    private val userDetailsService: UserDetailsService
): OncePerRequestFilter(){
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        if(request.servletPath.contains("api/v1/auth")){
            filterChain.doFilter(request,response)
            return
        }

        val authHeader = request.getHeader("Authorization")

        //1. Validar que exista el header y empiece con Bearer
        if(authHeader == null || !authHeader.startsWith("Bearer")){
            filterChain.doFilter(request,response)
            return
        }

        //2. Sacamos el token
        val jwt = authHeader.substring(7)
        val userEmail = jwtService.extractUsername(jwt) //email

        //3. Hay email y no autenticacion
        if(userEmail != null && SecurityContextHolder.getContext().authentication == null) {
            //Traer los userDetails
            val userDetails = this.userDetailsService.loadUserByUsername(userEmail)

            //4. Validar token
            if(jwtService.isTokenValid(jwt, userDetails)){
                //Crear autenticacion
                val authToken = UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.authorities
                )

                authToken.details = WebAuthenticationDetailsSource().buildDetails(request)

                //5. Actualizamos el contexto
                SecurityContextHolder.getContext().authentication = authToken
            }

        }
        filterChain.doFilter(request,response)
    }
}