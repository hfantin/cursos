package br.com.alura.forum.controller

import br.com.alura.forum.model.asTopicoDto
import br.com.alura.forum.repository.TopicoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort.Direction
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/v1/topicos")
class TopicosController {

    @Autowired
    private lateinit var topicoRepository: TopicoRepository

    @GetMapping
    @Cacheable("lista-topicos")
    fun listar(@PageableDefault(sort = ["dataCriacao"], direction = Direction.DESC, page = 0, size = 10) paginacao: Pageable) =
            topicoRepository.findAll(paginacao).map { it.asTopicoDto() }

}

