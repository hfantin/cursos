package br.com.alura.forum.form

import br.com.alura.forum.model.Topico
import br.com.alura.forum.repository.CursoRepository
import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull


data class TopicoForm(
        @NotNull @NotEmpty @Length(min = 5)
        var titulo: String? = null,
        @NotNull @NotEmpty @Length(min = 10)
        var mensagem: String? = null,
        @NotNull @NotEmpty
        var nomeCurso: String = "")

fun TopicoForm.converter(cursoRepository: CursoRepository) =
        Topico(titulo = titulo, mensagem = mensagem, curso = cursoRepository.findByNome(nomeCurso))
