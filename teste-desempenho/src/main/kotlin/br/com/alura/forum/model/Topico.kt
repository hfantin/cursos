package br.com.alura.forum.model

import br.com.alura.forum.dto.TopicoDto
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Topico(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        var titulo: String? = null,
        var mensagem: String? = null,
        var dataCriacao: LocalDateTime = LocalDateTime.now(),
        @Enumerated(EnumType.STRING)
        var status: StatusTopico = StatusTopico.NAO_RESPONDIDO
//        @ManyToOne
//        var curso: Curso? = null,
//        @OneToMany(mappedBy = "topico")
//        var respostas: MutableList<Resposta> = mutableListOf()
)

fun Topico.asTopicoDto() = TopicoDto(id, titulo, mensagem, dataCriacao)
