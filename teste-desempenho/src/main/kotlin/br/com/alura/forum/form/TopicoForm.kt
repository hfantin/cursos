package br.com.alura.forum.form

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull


data class TopicoForm(
        @field:NotNull @field:NotEmpty @field:Length(min = 5)
        var titulo: String? = null,
        @field:NotNull @field:NotEmpty @field:Length(min = 10)
        var mensagem: String? = null,
        @field:NotNull @field:NotEmpty
        var nomeCurso: String = "")

//fun TopicoForm.converter(cursoRepository: CursoRepository) =
//        Topico(titulo = titulo, mensagem = mensagem, curso = cursoRepository.findByNome(nomeCurso))
