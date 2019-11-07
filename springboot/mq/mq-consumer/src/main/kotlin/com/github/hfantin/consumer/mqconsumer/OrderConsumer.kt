package com.github.hfantin.consumer.mqconsumer

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component
import org.slf4j.LoggerFactory



@Component
class OrderConsumer {

    private val log = LoggerFactory.getLogger(OrderConsumer::class.java)

    @RabbitListener(queues = arrayOf("\${queue.order.name}"))
    fun receive(@Payload order: String) {
        log.info("consumer.receive")
        GlobalScope.launch{
            log.info("inicio da coroutine...")
            delay(2000)
            log.info("fim da coroutine - $order")
        }
        log.info("order: $order")
    }
}