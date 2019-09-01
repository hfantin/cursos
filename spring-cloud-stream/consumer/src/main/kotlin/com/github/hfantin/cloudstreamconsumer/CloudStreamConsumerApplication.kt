package com.github.hfantin.cloudstreamconsumer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CloudStreamConsumerApplication

fun main(args: Array<String>) {
	runApplication<CloudStreamConsumerApplication>(*args)
}
