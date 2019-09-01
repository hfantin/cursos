package com.github.hfantin.cloudstreamconsumer.consumers

import org.slf4j.LoggerFactory
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.cloud.stream.messaging.Sink

@EnableBinding(Sink::class)
class Consumer {

    private var logger = LoggerFactory.getLogger(javaClass.simpleName)

    @StreamListener(Sink.INPUT)
    fun readMessage(message: String){
        logger.info("mensagem recebida: $message")
    }
}