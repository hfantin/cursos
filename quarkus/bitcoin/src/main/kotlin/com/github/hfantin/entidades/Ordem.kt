package com.github.hfantin.entidades

import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
data class Ordem(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0L,
        var preco: Double = 0.0,
        @field:NotBlank(message="Tipo invalido")
        var tipo: String = "",
        var data: LocalDate = LocalDate.now(),
        var status: String = "Enviada",
        @Column(name = "user_id")
        var userId: Long = 0
)