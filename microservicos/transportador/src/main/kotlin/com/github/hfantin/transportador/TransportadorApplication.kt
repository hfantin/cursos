package com.github.hfantin.transportador

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class TransportadorApplication

fun main(args: Array<String>) {
	runApplication<TransportadorApplication>(*args)
}
