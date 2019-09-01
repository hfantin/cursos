package com.github.hfantin.cloudstreamconsumer

import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.cloud.stream.messaging.Sink

@EnableBinding(Sink::class)
class Consumer {

    @StreamListener(Sink.INPUT)
    fun readMessage(message: String){
        println("mensagem recebida: $message")
    }
}