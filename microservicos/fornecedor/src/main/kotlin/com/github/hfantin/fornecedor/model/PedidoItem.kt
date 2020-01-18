package com.github.hfantin.fornecedor.model

import javax.persistence.*

@Entity
data class PedidoItem(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,

        var quantidade: Int = 0,

        @ManyToOne
        @JoinColumn(name = "produtoId")
        var produto: Produto? = null
)