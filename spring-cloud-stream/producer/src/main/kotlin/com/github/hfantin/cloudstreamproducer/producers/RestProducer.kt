package com.github.hfantin.cloudstreamproducer.producers

import com.github.hfantin.cloudstreamproducer.sources.RestSource
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component

@Component
class RestProducer {

    fun sendMessageToTopic(msg: String, restSource: RestSource): Boolean {
        val message = MessageBuilder.withPayload(msg).build()
        val sucesso = restSource.sendToTopic().send(message)
        return sucesso
    }

    fun sendMessageToQueue(msg: String, restSource: RestSource): Boolean {
        val message = MessageBuilder.withPayload(msg).build()
        val sucesso = restSource.sendToQueue().send(message)
        return sucesso
    }
}