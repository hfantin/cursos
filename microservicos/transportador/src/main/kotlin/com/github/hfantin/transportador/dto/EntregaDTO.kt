package com.github.hfantin.transportador.dto

import java.time.LocalDate

data class EntregaDTO(var pedidoId: Long = 0, var dataParaEntrega: LocalDate = LocalDate.now(), var enderecoOrigem: String = "", var enderecoDestino: String = "")