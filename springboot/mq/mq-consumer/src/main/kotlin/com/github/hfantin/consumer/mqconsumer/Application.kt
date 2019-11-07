package com.github.hfantin.consumer.mqconsumer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MqConsumerApplication

fun main(args: Array<String>) {
	runApplication<MqConsumerApplication>(*args)
}

