package br.com.alura.forum.controller

import br.com.alura.forum.dto.TopicoDto
import br.com.alura.forum.extensions.findByIdOrNotFound
import br.com.alura.forum.form.AtualizacaoTopicoForm
import br.com.alura.forum.form.TopicoForm
import br.com.alura.forum.form.converter
import br.com.alura.forum.model.asDetaheTopicoDto
import br.com.alura.forum.model.asTopicoDto
import br.com.alura.forum.repository.CursoRepository
import br.com.alura.forum.repository.TopicoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort.Direction
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
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
    fun listar(@RequestParam(required = false) nomeCurso: String?,
               @PageableDefault(sort = ["dataCriacao"], direction = Direction.DESC, page = 0, size = 10) paginacao: Pageable) =
            listarTopicos(nomeCurso, paginacao).map { it.asTopicoDto() }

    private fun listarTopicos(nomeCurso: String?, paginacao: Pageable) = nomeCurso?.let {
        topicoRepository.findByCursoNome(nomeCurso, paginacao)
    } ?: topicoRepository.findAll(paginacao)

//    @GetMapping("/{id}")
//    fun detalhar(@PathVariable id: Long) = topicoRepository.findByIdOrNull(id)?.let { ResponseEntity.ok(it.asDetaheTopicoDto()) } ?: ResponseEntity.notFound().build()

    @GetMapping("/{id}")
    fun detalhar(@PathVariable id: Long) = topicoRepository.findByIdOrNotFound(id) { it.asDetaheTopicoDto() }

    @PostMapping
    @Transactional
    fun cadastrar(@RequestBody @Valid form: TopicoForm, uriBuilder: UriComponentsBuilder): ResponseEntity<TopicoDto> {
        val topico = form.converter(cursooRepository)
        topicoRepository.save(topico);
        val uri = uriBuilder.path("/v1/topicos/{id}").buildAndExpand(topico.id).toUri()
        return ResponseEntity.created(uri).body(topico.asTopicoDto())
    }

//    @PutMapping("/{id}")
//    @Transactional
//    fun atualizar(@PathVariable id: Long, @RequestBody @Valid form: AtualizacaoTopicoForm) =
//            topicoRepository.findByIdOrNull(id)?.let {
//                ResponseEntity.ok(it.also {
//                    it.titulo = form.titulo
//                    it.mensagem = form.mensagem
//                }.asDetaheTopicoDto())
//            } ?: ResponseEntity.notFound().build()

    @PutMapping("/{id}")
    @Transactional
    fun atualizar(@PathVariable id: Long, @RequestBody @Valid form: AtualizacaoTopicoForm) = topicoRepository.findByIdOrNotFound(id) {
        it.also { topico ->
            topico.titulo = form.titulo
            topico.mensagem = form.mensagem
        }.asDetaheTopicoDto()
    }

    @DeleteMapping("/{id}")
    @Transactional
    fun remover(@PathVariable id: Long): ResponseEntity<Any> {
        if (topicoRepository.existsById(id)) {
            topicoRepository.deleteById(id)
            return ResponseEntity.ok().build()
        }
        return ResponseEntity.notFound().build()
    }

}

