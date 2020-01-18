package com.github.hfantin.loja.service

import com.github.hfantin.loja.client.FornecedorClient
import com.github.hfantin.loja.controller.dto.CompraDTO
import com.github.hfantin.loja.controller.dto.InfoFornecedorDTO
import com.github.hfantin.loja.model.Compra
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
    private lateinit var fornecedorClient: FornecedorClient

//    @Autowired
//    private lateinit var client: RestTemplate

    fun realizaCompra(compra: CompraDTO): Compra {
        logger.info("fazer compra")
/*
        val exchange: ResponseEntity<InfoFornecedorDTO> = client.exchange("http://fornecedor/info/" + compra?.endereco?.estado,
                HttpMethod.GET, null, InfoFornecedorDTO::class.java)
        logger.info("endereço: {}", exchange.getBody()?.endereco)
*/

        val pedido = fornecedorClient.realizaPedido(compra.itens)
        val info = fornecedorClient.getInfoProEstado(compra.endereco.estado)
        logger.info("pedido via feign: {}", pedido)
        logger.info("info via feign: {}", info)
        return Compra(pedido.id, pedido.tempoDePreparo, "${compra.endereco.rua}, ${compra.endereco.numero} - ${compra.endereco.estado}")
    }
}