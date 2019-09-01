package com.github.hfantin.cloudstreamproducer.controllers

import com.github.hfantin.cloudstreamproducer.model.Teste
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import com.github.hfantin.cloudstreamproducer.producers.TesteProducer
import com.github.hfantin.cloudstreamproducer.sources.TesteSource
import org.slf4j.LoggerFactory


@RestController
class TesteController {

    private var logger = LoggerFactory.getLogger(javaClass.simpleName)


    @Autowired
    private lateinit var testeSource: TesteSource

    @Autowired
    private lateinit var testeProducer: TesteProducer

    @GetMapping("/v1/enviar/{message}")
    fun sendToTopic(@PathVariable message: String): String {
        logger.info("enviando mensagem $message")
        val result = testeProducer.send(Teste(message, "191"), testeSource)
        return  if(result) "mensagem $message enviada com sucesso!" else "falha no envio da mensaegm $message..."
    }
}