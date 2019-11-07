package com.github.hfantin.sender

import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class OrderQueueSender {

    @Autowired
    private lateinit var rabbitTemplate: RabbitTemplate

    @Autowired
    private lateinit var queue: Queue

    fun send(order: String) {
        rabbitTemplate.convertAndSend(this.queue.name, order)
    }
}
