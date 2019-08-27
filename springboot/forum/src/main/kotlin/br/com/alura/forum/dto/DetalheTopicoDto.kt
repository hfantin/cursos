package br.com.alura.forum.dto

import br.com.alura.forum.model.StatusTopico
import java.time.LocalDateTime

data class DetalheTopicoDto(
        var id: Long? = null,
        var titulo: String? = null,
        var mensagem: String? = null,
        var dataCriacao: LocalDateTime = LocalDateTime.now(),
        var nomeAutor: String? = null,
        var status: StatusTopico = StatusTopico.NAO_RESPONDIDO,
        var respostas: List<RespostaDto> = listOf()
)