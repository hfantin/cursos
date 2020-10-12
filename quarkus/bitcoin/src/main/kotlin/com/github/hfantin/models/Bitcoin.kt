package com.github.hfantin.models

import java.time.LocalDate

data class Bitcoin(var id: Long=0L, var preco: Double=0.0, var tipo: String = "", var data: LocalDate= LocalDate.now())