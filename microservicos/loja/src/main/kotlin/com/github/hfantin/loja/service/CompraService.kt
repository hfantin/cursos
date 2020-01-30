package com.github.hfantin.loja.service

import com.github.hfantin.loja.client.FornecedorClient
import com.github.hfantin.loja.controller.dto.CompraDTO
import com.github.hfantin.loja.model.Compra
import com.github.hfantin.loja.repository.CompraRepository
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class CompraService {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    private lateinit var fornecedorClient: FornecedorClient

    @Autowired
    private lateinit var compraRepository: CompraRepository


    @HystrixCommand(threadPoolKey = "getByIdThreadPool")
    fun getById(id: Long) = compraRepository.findById(id).orElse(Compra())

//    @Autowired
//    private lateinit var client: RestTemplate

    @HystrixCommand(fallbackMethod = "realizaCompraFallback", threadPoolKey = "realizaCompraThreadPool")
    fun realizaCompra(compra: CompraDTO): Compra {
        logger.info("fazer compra")
//        Thread.sleep(5000L)
/*
        val exchange: ResponseEntity<InfoFornecedorDTO> = client.exchange("http://fornecedor/info/" + compra?.endereco?.estado,
                HttpMethod.GET, null, InfoFornecedorDTO::class.java)
        logger.info("endereço: {}", exchange.getBody()?.endereco)
*/

        val pedido = fornecedorClient.realizaPedido(compra.itens)
        val info = fornecedorClient.getInfoProEstado(compra.endereco.estado)
        logger.info("pedido via feign: {}", pedido)
        logger.info("info via feign: {}", info)
        val compraSalva = Compra(pedido.id, pedido.tempoDePreparo, "${compra.endereco.rua}, ${compra.endereco.numero} - ${compra.endereco.estado}")
        compraRepository.save(compraSalva)
        return compraSalva
    }

    fun realizaCompraFallback(compra: CompraDTO): Compra {
        logger.info("fallback");
        return Compra().apply { endereco = "fallback ${compra.endereco.rua}, ${compra.endereco.numero} - ${compra.endereco.estado}" }
    }
}