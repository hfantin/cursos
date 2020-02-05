package com.github.hfantin.loja.dto

import java.time.LocalDate

data class InfoEntregaDTO(
        var pedidoId: Long = 0,
        var dataParaEntrega: LocalDate = LocalDate.now(),
        var enderecoOrigem:  String = "",
        var enderecoDestino: String = ""
)