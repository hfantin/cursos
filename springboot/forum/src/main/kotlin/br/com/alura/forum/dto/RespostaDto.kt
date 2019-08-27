package br.com.alura.forum.dto

import java.time.LocalDateTime

data class RespostaDto(
        var id: Long? = null,
        var mensagem: String? = null,
        var dataCriacao: LocalDateTime = LocalDateTime.now(),
        var nomeAutor: String? = null
)
