package com.github.hfantin.loja.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Compra(
        @Id
//        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var pedidoId: Long = 0,
        var tempoDePreparo: Int =0,
        var endereco: String = ""
)