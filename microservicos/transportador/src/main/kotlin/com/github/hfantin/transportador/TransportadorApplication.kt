package com.github.hfantin.transportador

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer

@SpringBootApplication
@EnableDiscoveryClient
@EnableResourceServer
class TransportadorApplication

fun main(args: Array<String>) {
	runApplication<TransportadorApplication>(*args)
}
