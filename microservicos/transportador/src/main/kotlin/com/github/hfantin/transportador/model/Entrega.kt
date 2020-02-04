package com.github.hfantin.transportador.model

import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Entrega(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,
        var pedidoId: Long = 0,
        var dataParaBusca: LocalDate = LocalDate.now(),
        var previsaoParaEntrega: LocalDate = LocalDate.now(),
        var enderecoOrigem: String = "",
        var enderecoDestino: String = ""
)