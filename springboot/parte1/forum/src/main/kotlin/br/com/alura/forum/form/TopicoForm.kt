package br.com.alura.forum.form

import br.com.alura.forum.model.Topico
import br.com.alura.forum.repository.CursoRepository
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import org.hibernate.validator.constraints.Length


data class TopicoForm(
        @field:NotNull @field:NotEmpty @field:Length(min = 5)
        var titulo: String? = null,
        @field:NotNull @field:NotEmpty @field:Length(min = 10)
        var mensagem: String? = null,
        @field:NotNull @field:NotEmpty
        var nomeCurso: String = "")

fun TopicoForm.converter(cursoRepository: CursoRepository) =
        Topico(titulo = titulo, mensagem = mensagem, curso = cursoRepository.findByNome(nomeCurso))
