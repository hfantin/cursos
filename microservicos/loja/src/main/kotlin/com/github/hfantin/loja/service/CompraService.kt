package com.github.hfantin.loja.service

import com.github.hfantin.loja.controller.dto.CompraDTO
import com.github.hfantin.loja.controller.dto.InfoFornecedorDTO
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class CompraService {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    private lateinit var client: RestTemplate

    fun realizaCompra(compra: CompraDTO?) {
        val exchange: ResponseEntity<InfoFornecedorDTO> = client.exchange("http://fornecedor/info/" + compra?.endereco?.estado,
                HttpMethod.GET, null, InfoFornecedorDTO::class.java)
        logger.info("endereço: {}", exchange.getBody()?.endereco)
    }
}