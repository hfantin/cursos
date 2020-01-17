package com.github.hfantin.fornecedor

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class FornecedorApplication

fun main(args: Array<String>) {
	runApplication<FornecedorApplication>(*args)
}
