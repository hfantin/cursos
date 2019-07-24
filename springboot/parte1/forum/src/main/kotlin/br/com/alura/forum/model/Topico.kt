package br.com.alura.forum.model

import java.time.LocalDateTime

data class Topico(
        val id: Long? = null,
        val titulo: String? = null,
        val mensagem: String? = null,
        val dataCriacao: LocalDateTime = LocalDateTime.now(),
        val status: StatusTopico = StatusTopico.NAO_RESPONDIDO,
        val autor: Usuario? = null,
        val curso: Curso? = null,
        val respostas: MutableList<Resposta> = mutableListOf()
)
