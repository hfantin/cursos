package br.com.alura.forum.model

import java.time.LocalDateTime

data class Resposta(
        val id: Long? = null,
        val mensagem: String? = null,
        val topico: Topico? = null,
        val dataCriacao: LocalDateTime = LocalDateTime.now(),
        val autor: Usuario? = null,
        val solucao: Boolean = false
)
