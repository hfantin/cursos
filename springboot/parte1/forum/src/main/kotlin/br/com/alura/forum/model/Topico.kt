package br.com.alura.forum.model

import br.com.alura.forum.dto.TopicoDto
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Topico(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        val titulo: String? = null,
        val mensagem: String? = null,
        val dataCriacao: LocalDateTime = LocalDateTime.now(),
        @Enumerated(EnumType.STRING)
        val status: StatusTopico = StatusTopico.NAO_RESPONDIDO,
        @ManyToOne
        val autor: Usuario? = null,
        @ManyToOne
        val curso: Curso? = null,
        @OneToMany(mappedBy = "topico")
        val respostas: MutableList<Resposta> = mutableListOf()
)

fun Topico.toDto() = TopicoDto(id, titulo, mensagem, dataCriacao)