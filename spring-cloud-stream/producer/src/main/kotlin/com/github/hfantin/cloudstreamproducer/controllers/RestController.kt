package com.github.hfantin.cloudstreamproducer.controllers

import com.github.hfantin.cloudstreamproducer.producers.Producer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import com.github.hfantin.cloudstreamproducer.producers.RestProducer
import com.github.hfantin.cloudstreamproducer.sources.RestSource



@RestController
class RestController {


    @Autowired
    private lateinit var restSource: RestSource

    @Autowired
    private lateinit var restProducer: RestProducer

    @GetMapping("/v1/topico/{message}")
    fun sendToTopic(@PathVariable message: String): String {
        println("enviando mensagem $message")
        val result = restProducer.sendMessageToTopic(message, restSource)
        return  "funcionou? $result"
    }

    @GetMapping("/v1/fila/{message}")
    fun sendToQueue(@PathVariable message: String): String {
        println("enviando mensagem $message")
        val result = restProducer.sendMessageToQueue(message, restSource)
        return  "funcionou? $result"
    }
}