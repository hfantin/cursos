package com.github.hfantin.loja.model

import java.time.LocalDate
import javax.persistence.*

@Entity
data class Compra(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long =0,
        var pedidoId: Long = 0,
        var tempoDePreparo: Long = 0,
        var endereco: String = "",
        var dataParaEntrega: LocalDate = LocalDate.now(),
        var vourcher: Long = 0,
        @Enumerated(EnumType.STRING)
        var state: CompraState = CompraState.RECEBIDO
)