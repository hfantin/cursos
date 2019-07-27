package br.com.alura.forum.controller

import br.com.alura.forum.dto.TopicoDto
import br.com.alura.forum.form.TopicoForm
import br.com.alura.forum.form.converter
import br.com.alura.forum.model.Topico
import br.com.alura.forum.model.toDto
import br.com.alura.forum.repository.CursoRepository
import br.com.alura.forum.repository.TopicoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid


@RestController
@RequestMapping("/v1/topicos")
class TopicosController {

    @Autowired
    private lateinit var topicoRepository: TopicoRepository

    @Autowired
    private lateinit var cursooRepository: CursoRepository

    @GetMapping
    fun lista(nomeCurso: String?) = listarTopicos(nomeCurso).map { it.toDto() }

    private fun listarTopicos(nomeCurso: String?): List<Topico> {
        return nomeCurso?.let {
            topicoRepository.findByCursoNome(nomeCurso)
        } ?: topicoRepository.findAll()
    }

    @PostMapping
    fun cadastrar(@RequestBody @Valid form: TopicoForm, uriBuilder: UriComponentsBuilder): ResponseEntity<TopicoDto> {
        val topico = form.converter(cursooRepository)
        topicoRepository.save(topico);
        val uri = uriBuilder.path("/v1/topicos/{id}").buildAndExpand(topico.id).toUri()
        return ResponseEntity.created(uri).body(topico.toDto())
    }

}

