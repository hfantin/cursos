package com.github.hfantin.transportador.service

import com.github.hfantin.transportador.dto.EntregaDTO
import com.github.hfantin.transportador.dto.VoucherDTO
import com.github.hfantin.transportador.model.Entrega
import com.github.hfantin.transportador.repository.EntregaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EntregaService {
    @Autowired
    private lateinit var repository: EntregaRepository

    fun reservaEntrega(pedidoDTO: EntregaDTO): VoucherDTO {
        val entrega = Entrega().apply {
            dataParaBusca = pedidoDTO.dataParaEntrega
            previsaoParaEntrega = pedidoDTO.dataParaEntrega.plusDays(1L)
            enderecoDestino = pedidoDTO.enderecoDestino
            enderecoOrigem = pedidoDTO.enderecoOrigem
            pedidoId = pedidoDTO.pedidoId
        }
        repository.save(entrega)
        return VoucherDTO().apply {
            numero = entrega.id
            previsaoParaEntrega = entrega.previsaoParaEntrega
        }
    }
}