package br.com.alura.forum.controller

import br.com.alura.forum.repository.TopicoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/topicos")
class TopicosController {

    @Autowired
    private lateinit var topicoRepository: TopicoRepository

    @RequestMapping("")
    fun lista(nomeCurso: String?) = nomeCurso?.let {
        topicoRepository.findByCursoNome(nomeCurso)
    }?: topicoRepository.findAll()


}

