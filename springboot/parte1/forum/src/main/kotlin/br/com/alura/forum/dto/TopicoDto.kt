package br.com.alura.forum.dto

import br.com.alura.forum.model.Topico
import java.time.LocalDateTime

data class TopicoDto(
        var id: Long? = null,
        var titulo: String? = null,
        var mensagem: String? = null,
        var dataCriacao: LocalDateTime = LocalDateTime.now()
)

fun Topico.toDto() = TopicoDto(id, titulo, mensagem, dataCriacao)