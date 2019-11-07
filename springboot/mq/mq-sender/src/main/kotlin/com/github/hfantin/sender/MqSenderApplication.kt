package com.github.hfantin.sender

import org.springframework.amqp.core.Queue
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class MqSenderApplication {

	@Value("\${queue.order.name}")
	private lateinit var orderQueue: String


	@Bean
	fun queue(): Queue {
		return Queue(orderQueue, true)
	}
}

fun main(args: Array<String>) {
	runApplication<MqSenderApplication>(*args)
}