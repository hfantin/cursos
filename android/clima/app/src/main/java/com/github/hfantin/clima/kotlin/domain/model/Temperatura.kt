package com.github.hfantin.clima.kotlin.domain.model

import java.util.Date

data class Temperatura(val icone: Int,
                       val data: Date,
                       val descricao: String,
                       val temperaturaMaxima: Double,
                       val temperaturaMinima: Double)
