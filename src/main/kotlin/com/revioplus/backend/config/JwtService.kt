package com.revioplus.backend.config

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.Date
import javax.crypto.SecretKey

@Service
class JwtService(
    @Value("\${jwt.secret}") private val secretKey: String,
    @Value("\${jwt.expiration}") private val jwtExpiration: Long,
) {
    //Construccion del token JWT
    fun generateToken(userDetails: UserDetails): String{
        return buildToken(HashMap(), userDetails, jwtExpiration)
    }

    private fun buildToken(
        extraClaims: Map<String,Any>,
        userDetails: UserDetails,
        jwtExpiration: Long
    ): String {
        return Jwts.builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.username)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + jwtExpiration))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact()
    }

    private fun getSignInKey(): SecretKey{
        val keyBytes = Decoders.BASE64.decode(secretKey)
        return Keys.hmacShaKeyFor(keyBytes)
    }

    //Verificacion de Tokens
    fun isTokenValid(token: String, userDetails: UserDetails): Boolean {
        val username = extractUsername(token)
        return (username == userDetails.username) && !isTokenExpired(token)
    }

     fun extractUsername(token: String): String{
        return extractClaim(token, Claims::getSubject)
    }

    private fun <T> extractClaim(token:String, claimsResolver: (Claims) -> T): T{
        val claims = extractAllClaims(token)
        return claimsResolver(claims)
    }

    private fun extractAllClaims(token:String) : Claims{
        return Jwts.parserBuilder()
            .setSigningKey(getSignInKey())
            .build()
            .parseClaimsJws(token)
            .body
    }

    private fun isTokenExpired(token: String): Boolean{
        return extractExpiration(token).before(Date())
    }

    private fun extractExpiration(token:String) : Date{
        return extractClaim(token, Claims::getExpiration)
    }

}