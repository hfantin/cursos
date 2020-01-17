package com.github.hfantin.loja

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class LojaApplication

fun main(args: Array<String>) {
	runApplication<LojaApplication>(*args)
}
