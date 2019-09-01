package com.github.hfantin.cloudstreamproducer.sources

import org.springframework.cloud.stream.annotation.Output
import org.springframework.messaging.MessageChannel

interface TesteSource {
    @Output("teste-channel")
    fun sendToTestChannel(): MessageChannel

}