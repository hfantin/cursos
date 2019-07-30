package br.com.alura.forum.config.security

import br.com.alura.forum.model.Usuario
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class TokenService {

    @Value("\${forum.jwt.expiration}")
    private lateinit var expiration: String

    @Value("\${forum.jwt.secret}")
    private lateinit var secret: String

    fun gerarToken(auth: Authentication): String {
        val logado = auth.principal as Usuario
        val hoje = Date()
        val dataExpiracao = Date(hoje.time + expiration.toLong())
        return Jwts.builder()
                .setIssuer("API do forum alura")
                .setSubject(logado.id.toString())
                .setIssuedAt(hoje)
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact()
    }

}