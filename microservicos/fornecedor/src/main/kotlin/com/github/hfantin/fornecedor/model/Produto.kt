package com.github.hfantin.fornecedor.model

import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Produto(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,
        var nome: String = "",
        var estado: String = "",
        var descricao: String = "",
        var preco: BigDecimal = BigDecimal.ZERO

)