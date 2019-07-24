package br.com.alura.forum.controller

import br.com.alura.forum.dto.toDto
import br.com.alura.forum.model.Curso
import br.com.alura.forum.model.Topico
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/topicos")
class TopicosController {

    private val topicos = listOf(
            Topico(id = 1, titulo = "Duvida 1", mensagem = "duvida com spring boot", curso = Curso(nome = "springboot parte 1")),
            Topico(id = 2, titulo = "Duvida 2", mensagem = "null pointer exception 3", curso = Curso(nome = "springboot parte 1")),
            Topico(id = 3, titulo = "Duvida 3", mensagem = "duvida com spring boot", curso = Curso(nome = "springboot parte 1"))
    )

    @RequestMapping("")
    fun lista() = topicos.map { it.toDto() }


}

