package com.github.hfantin.fornecedor

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer

@SpringBootApplication
@EnableDiscoveryClient
@EnableResourceServer
class FornecedorApplication

fun main(args: Array<String>) {
	runApplication<FornecedorApplication>(*args)
}
