package br.com.alura.forum.form

import br.com.alura.forum.model.Topico
import br.com.alura.forum.repository.CursoRepository
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import org.hibernate.validator.constraints.Length


data class AtualizacaoTopicoForm(
        @field:NotNull @field:NotEmpty @field:Length(min = 5)
        var titulo: String? = null,
        @field:NotNull @field:NotEmpty @field:Length(min = 10)
        var mensagem: String? = null
)

fun AtualizacaoTopicoForm.converter() =
        Topico(titulo = titulo, mensagem = mensagem)
