package br.com.alura.forum.form

import br.com.alura.forum.model.Topico
import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull


data class AtualizacaoTopicoForm(
        @field:NotNull @field:NotEmpty @field:Length(min = 5)
        var titulo: String? = null,
        @field:NotNull @field:NotEmpty @field:Length(min = 10)
        var mensagem: String? = null
)

fun AtualizacaoTopicoForm.converter() =
        Topico(titulo = titulo, mensagem = mensagem)
