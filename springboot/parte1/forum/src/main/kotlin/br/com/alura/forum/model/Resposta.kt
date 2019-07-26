package br.com.alura.forum.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Resposta(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        val mensagem: String? = null,
        @ManyToOne
        val topico: Topico? = null,
        val dataCriacao: LocalDateTime = LocalDateTime.now(),
        @ManyToOne
        val autor: Usuario? = null,
        val solucao: Boolean = false
)
