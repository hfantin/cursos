package com.github.hfantin.cloudstreamproducer.producers

import com.github.hfantin.cloudstreamproducer.model.Teste
import com.github.hfantin.cloudstreamproducer.sources.TesteSource
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component

@Component
class TesteProducer {

    fun send(msg: Teste, testeSource: TesteSource): Boolean {
        val message = MessageBuilder.withPayload(msg).build()
        return testeSource.sendToTestChannel().send(message)
    }

}