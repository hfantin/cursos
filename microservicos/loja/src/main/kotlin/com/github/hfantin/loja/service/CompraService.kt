package com.github.hfantin.loja.service

import com.github.hfantin.loja.client.FornecedorClient
import com.github.hfantin.loja.client.TransportadorClient
import com.github.hfantin.loja.dto.CompraDTO
import com.github.hfantin.loja.dto.InfoEntregaDTO
import com.github.hfantin.loja.model.Compra
import com.github.hfantin.loja.model.CompraState
import com.github.hfantin.loja.repository.CompraRepository
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate


@Service
class CompraService {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    private lateinit var fornecedorClient: FornecedorClient

    @Autowired
    private lateinit var transportadorClient: TransportadorClient

    @Autowired
    private lateinit var compraRepository: CompraRepository


    @HystrixCommand(threadPoolKey = "getByIdThreadPool")
    fun getById(id: Long) = compraRepository.findById(id).orElse(Compra())

//    @Autowired
//    private lateinit var client: RestTemplate

    @HystrixCommand(fallbackMethod = "realizaCompraFallback", threadPoolKey = "realizaCompraThreadPool")
    fun realizaCompra(compra: CompraDTO): Compra {

//        Thread.sleep(5000L)
/*
        val exchange: ResponseEntity<InfoFornecedorDTO> = client.exchange("http://fornecedor/info/" + compra?.endereco?.estado,
                HttpMethod.GET, null, InfoFornecedorDTO::class.java)
        logger.info("endereço: {}", exchange.getBody()?.endereco)
*/

        logger.info("salva estado inicial da compra")

        // Salva estado inicial da compra
        val compraSalva = Compra().apply {
            state = CompraState.RECEBIDO
            endereco = "${compra.endereco.rua}, ${compra.endereco.numero} - ${compra.endereco.estado}"
        }
        compraRepository.save(compraSalva)
        compra.compraId = compraSalva.id


        logger.info("realiza pedido")
        // realiza pedido
        val info = fornecedorClient.getInfoProEstado(compra.endereco.estado)
        val pedido = fornecedorClient.realizaPedido(compra.itens)
        logger.info("pedido via feign: {}", pedido)
        logger.info("info via feign: {}", info)
        with(compraSalva) {
            this.state = CompraState.PEDIDO_REALIZADO
            this.pedidoId = pedido.id
            this.tempoDePreparo = pedido.tempoDePreparo
        }
        compraRepository.save(compraSalva)

        // grava entrega
        logger.info("grava entrega")
        val entregaDto = InfoEntregaDTO().apply {
            pedidoId = pedido.id
            dataParaEntrega = LocalDate.now().plusDays(pedido.tempoDePreparo)
            enderecoOrigem = info.endereco ?: ""

        }
        val voucher = transportadorClient.reservaEntrega(entregaDto)
        with(compraSalva) {
            this.state = CompraState.RESERVA_ENTREGA_REALIZADA
            this.dataParaEntrega = voucher.previsaoParaEntrega
            this.vourcher = voucher.numero
        }
		compraRepository.save(compraSalva)

        return compraSalva
    }

    fun realizaCompraFallback(compra: CompraDTO): Compra {
        logger.info("fallback");
        if(compra.compraId > 0) {
            return compraRepository.findById(compra.compraId).get()
        }
        return Compra().apply { endereco = "fallback ${compra.endereco.rua}, ${compra.endereco.numero} - ${compra.endereco.estado}" }
    }
}