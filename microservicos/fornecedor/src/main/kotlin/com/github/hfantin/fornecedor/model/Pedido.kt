package com.github.hfantin.fornecedor.model

import javax.persistence.*

@Entity
data class Pedido(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,

        var tempoDePreparo: Int = 0,

        @Enumerated(EnumType.STRING)
        var status: PedidoStatus = PedidoStatus.RECEBIDO,

        @OneToMany(cascade = [CascadeType.ALL])
        @JoinColumn(name = "pedidoId")
        var itens: List<PedidoItem> = listOf()

)