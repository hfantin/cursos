package com.github.hfantin.cloudstreamproducer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CloudStreamProducerApplication

fun main(args: Array<String>) {
	runApplication<CloudStreamProducerApplication>(*args)
}
