package br.com.alura.forum.dto

import java.time.LocalDateTime

data class TopicoDto(
        var id: Long? = null,
        var titulo: String? = null,
        var mensagem: String? = null,
        var dataCriacao: LocalDateTime = LocalDateTime.now()
)
