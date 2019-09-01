package com.github.hfantin.cloudstreamproducer.sources

import org.springframework.cloud.stream.annotation.Output
import org.springframework.messaging.MessageChannel

interface RestSource {
    @Output("topico.hamilton")
    fun sendToTopic(): MessageChannel

    @Output("fila.hamilton")
    fun sendToQueue(): MessageChannel
}